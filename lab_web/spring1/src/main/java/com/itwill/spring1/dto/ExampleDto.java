package com.itwill.spring1.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder    // Builder 패턴 내부클래스와 static 메서드를 자동으로 만들어줌.
// @Data = @ToString + @EqualsAndHashCode + @Getter + @Setter + @RequiredArgsConstructor
// @NoArgsConstructor: 아규먼트를 갖지 않는 생성자. 기본 생성자.
// @AllArgsConstructor: 모든 아규먼트를 갖는 생성자.
// @RequiredArgsConstructor: final 필드들만 아규먼트로 갖는 생성자.
public class ExampleDto {
    // 요청 파마리터 이름과 같게 필드 이름 선언
    private final String username;
    private final int age;
    
    
    
}
