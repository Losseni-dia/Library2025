package be.iccbxl.poo.mylibrary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraphicNovel extends Book {
    private String cartoonist;
    private boolean color;
    
}
