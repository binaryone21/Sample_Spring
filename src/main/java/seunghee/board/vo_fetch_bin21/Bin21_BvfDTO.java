package seunghee.board.vo_fetch_bin21;

import lombok.Data;

@Data
public class Bin21_BvfDTO {
    /* fetch_api 는 소문자만 취급한다 */
    private String tp_pk;
    private String tp_age;
    private String tp_name;
    private String tp_job;
}
