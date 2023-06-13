package city.olooe.plogging_project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO {
  private String error;
  private List<?> data;
}

//public class ResponseDTO<T> {
//  private String error;
//  private T data;
//}


