package com.example.lab2


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener{
            onStartButtonClick()
        }
    }

    suspend fun writeDatabase(rezult: List<Post>): String {

        return suspendCoroutine { continuation ->
            Thread{
                val db = getterDatabase.getInstance(activity as MainActivity)
                val tableInterface = db.bdtableinterface()
                tableInterface.delete()
                for (i in 0..rezult.size - 1) {
                    var table = bdTable()
                    table.id = i+1
                    table.image = rezult[i].getImage()
                    table.country = rezult[i].getCountry()
                    table.cost = rezult[i].getCost()
                    table.inform = rezult[i].getInform()
                    tableInterface.insert(table)
                }
                continuation.resume("Success")
            }.start()
        }
    }

    suspend fun readDatabase(): List<bdTable> {

        return suspendCoroutine { continuation ->
            Thread{
                val db = getterDatabase.getInstance(activity as MainActivity)
                val tableInterface = db.bdtableinterface()
                var rezult = tableInterface.getAll()
                continuation.resume(rezult)
            }.start()
        }
    }

    fun onStartButtonClick() {
        listLayout.removeAllViews()
        GlobalScope.launch(Dispatchers.Main) {
            var rez : List<Post> = longRunningMethod()
            if(rez.isNotEmpty()) {
                writeDatabase(rez)
                for (i in 0 until rez.size) {
                    var part: LinearLayout =layoutInflater.inflate(R.layout.part, null) as LinearLayout
                    var country: TextView =part.getChildAt(0) as TextView
                    var image: ImageView =part.getChildAt(1) as ImageView
                    var cost: TextView =part.getChildAt(2) as TextView
                    country.setText(rez[i].getCountry())
                    cost.setText(rez[i].getCost())
                    listLayout.addView(part)
                    Picasso.with(activity as MainActivity)
                        .load(rez[i].getImage())
                        .into(image)

                    part.setOnClickListener{
                        (activity as MainActivity).id1=rez[i].id
                        (activity as MainActivity).country=rez[i].country
                        (activity as MainActivity).price=rez[i].cost
                        (activity as MainActivity).inform=rez[i].inform
                        (activity as MainActivity).image=rez[i].getImage()
                        var fragment=ElementFragment()
                        (activity as MainActivity).fragmentReplace(fragment)
                    }
                }
            }
            else {
                var toast= Toast.makeText(activity as MainActivity,
                    "Грузим временные данные", Toast.LENGTH_SHORT)
                toast.show()
                var rezult: List<bdTable> =readDatabase()
                if(rezult.isEmpty())
                {
                    var toast= Toast.makeText(activity as MainActivity,
                        "В базе данных нет данных", Toast.LENGTH_SHORT)
                    toast.show()
                }
                else
                {
                    var im=0
                    var size: Int=rezult.size
                    do {
                        var part: LinearLayout =layoutInflater.inflate(R.layout.part, null) as LinearLayout
                        var country: TextView =part.getChildAt(0) as TextView
                        var image: ImageView =part.getChildAt(1) as ImageView
                        var cost: TextView =part.getChildAt(2) as TextView
                        var inform: TextView =part.getChildAt(3) as TextView
                        var button: Button = Button(activity as MainActivity)
                        button.setOnClickListener(View.OnClickListener {

                        })
                        country.setText(rezult[im].country)
                        cost.setText(rezult[im].cost)
                        inform.setText(rezult[im].inform)
                        mainLayout.addView(part)
                        Picasso.with(activity as MainActivity)
                            .load(rezult[im].image)
                            .into(image)
                        im++
                    }while(im<size)
                }
            }
        }
    }

}

suspend fun longRunningMethod() : List<Post>
{
    return suspendCoroutine { continuation ->
        NetworkService.getInstance()
            .getInterfaceJson()
            .getAllPosts()
            .enqueue(object : Callback<List<Post>> {
                override fun onResponse(
                    call: Call<List<Post>>,
                    response: Response<List<Post>>
                ) {
                    var rezult = response.body()
                    if (rezult != null) {
                        continuation.resume(rezult)

                    } else
                        continuation.resume(emptyList())
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    t.printStackTrace()
                    continuation.resume(emptyList())
                }
            })
    }

}
