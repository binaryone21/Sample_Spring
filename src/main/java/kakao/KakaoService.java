package kakao;

import org.springframework.stereotype.Service;

@Service
public class KakaoService {

    private KakaoDAO kkDao;

    public UebFaqVO select() {
        return kkDao.select();
    }
}
