import lombok.Data;
import lombok.*;

import java.util.List;

@With
public record Order(
        String id,
        List<Product> products,
        Status status

) {
}
