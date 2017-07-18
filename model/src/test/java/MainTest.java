import com.carrey.mvpjava.entity.HomeEntity;
import com.carrey.mvpjava.http.NameValuePair;
import com.carrey.mvpjava.http.RetrofitConfig;
import com.carrey.mvpjava.http.RetrofitFactory;
import com.carrey.mvpjava.http.SignHelper;
import com.carrey.mvpjava.http.SignInterceptor;
import com.carrey.mvpjava.repository.HomeDataSource;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import okhttp3.Headers;

/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc:
 */

public class MainTest {

    @Test
    public void testHome() throws Exception {

        initRetrofit();

        RetrofitFactory.INSTANCE.createService(HomeDataSource.class)
                .getHome("123456","haha")
                .subscribe(new Consumer<HomeEntity>() {
                    @Override
                    public void accept(HomeEntity homeEntity) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }

    @Test
    public void testHome1() throws Exception{
        initRetrofit();
        RetrofitFactory.INSTANCE.createService(HomeDataSource.class)
                .postHome1("123456","haha")
                .subscribe(new Consumer<HomeEntity>() {
                    @Override
                    public void accept(HomeEntity homeEntity) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
    @Test
    public void testHome2() throws Exception{
        initRetrofit();
        Map<String,String> map=new HashMap<>();
        map.put("123","456");
        map.put("1234","4516");
        map.put("12345","45126");
        map.put("123456","451236");
        RetrofitFactory.INSTANCE.createService(HomeDataSource.class)
                .postHome2(map)
                .subscribe(new Consumer<HomeEntity>() {
                    @Override
                    public void accept(HomeEntity homeEntity) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
    @Test
    public void testHome3() throws Exception{
        initRetrofit();
        RetrofitFactory.INSTANCE.createService(HomeDataSource.class)
                .postHome3("123456")
                .subscribe(new Consumer<HomeEntity>() {
                    @Override
                    public void accept(HomeEntity homeEntity) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
    @Test
    public void testHome4() throws Exception{
        initRetrofit();
        Map<String,String> map=new HashMap<>();
        map.put("123","456");
        map.put("1234","4516");
        map.put("12345","45126");
        map.put("123456","451236");
        RetrofitFactory.INSTANCE.createService(HomeDataSource.class)
                .postHome4(map)
                .subscribe(new Consumer<HomeEntity>() {
                    @Override
                    public void accept(HomeEntity homeEntity) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    } @Test
    public void testHome5() throws Exception{
        initRetrofit();
        Map<String,String> map=new HashMap<>();
        map.put("123","456");
        map.put("1234","4516");
        map.put("12345","45126");
        map.put("123456","451236");
        RetrofitFactory.INSTANCE.createService(HomeDataSource.class)
                .postHome5(map)
                .subscribe(new Consumer<HomeEntity>() {
                    @Override
                    public void accept(HomeEntity homeEntity) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public static void initRetrofit() {

        RetrofitFactory.initFactory(new RetrofitConfig.Builder()
                .setBaseUrl("http://gank.io/api/")
                .setLogDebugger(true)
                .setSignInterceptor(new SignInterceptor(new SignHelper() {
                    @Override
                    public ArrayList<NameValuePair> sortHeaders(Headers.Builder headersBuilder) {
                        return new ArrayList<NameValuePair>();
                    }

                    @Override
                    public NameValuePair createNameValuePair(final String name, final String value) {
                        return new NameValuePair() {
                            @Override
                            public String getName() {
                                return name;
                            }

                            @Override
                            public String getValue() {
                                return value;
                            }
                        };
                    }
                }))
                .build());
    }
}
