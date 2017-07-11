import com.carrey.mvpjava.http.RetrofitFactory;
import com.carrey.mvpjava.repository.HomeRepository;

import org.junit.Test;

/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc:
 */

public class HomeTest {

    @Test
    public void testHome() throws Exception {

        RetrofitFactory.INSTANCE.createService(HomeRepository.class)
                .getHome()
                .subscribe();

    }
}
