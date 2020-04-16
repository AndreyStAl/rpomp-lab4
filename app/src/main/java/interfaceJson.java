import com.example.lab2.Post;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface interfaceJson {
    @GET("/lab2")
    public Call<List<Post>> getAllPosts();
}
