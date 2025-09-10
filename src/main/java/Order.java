import lombok.Data;
import lombok.*;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

@With
public record Order(
        String id,
        List<Product> products,
        Status status,
        Instant time
) {

   public Order(String id,List<Product> products, Status status) {
       this(id, products, status, null);
   }
}
