package p.khj745700.coucoupang.application.domain.bucket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Bucket {
    private List<Integer> productList;
    private String memberId;
}
