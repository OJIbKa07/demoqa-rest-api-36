package models.lobmok;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteBookRequestLombokModel {

    private String userId;
    private String isbn;
}
