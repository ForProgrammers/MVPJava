import com.carrey.mvpjava.http.RetrofitConfig;
import com.carrey.mvpjava.http.RetrofitFactory;
import com.carrey.mvpjava.repository.HomeRepository;

import org.junit.Test;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc:
 */

public class MainTest {

    @Test
    public void testHome() throws Exception {

        initRetrofit();
        RetrofitFactory.INSTANCE.createService(HomeRepository.class)
                .getHome()
                .subscribe();

    }

    public static void initRetrofit() {

        RetrofitFactory.initFactory(new RetrofitConfig.Builder()
                .setBaseUrl("http://gank.io/api/")
                .setLogDebugger(true)
                .setSignInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader("api_version", "xxxxxx");
                        return chain.proceed(builder.build());
                    }
                })
                .build());
    }
}
