package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000")
public class Item {

//    @NotNull(groups = UpdateCheck.class)
    private Long id;

//    @NotBlank(message = "공백을 입력할 수 없습니다.", groups = {SaveCheck.class,UpdateCheck.class})
//    @NotBlank
    private String itemName;

//    @NotNull(groups = {SaveCheck.class,UpdateCheck.class})
//    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class,UpdateCheck.class})
//    @NotNull
//    @Range(min=1000, max= 1000000)
    private Integer price;

//    @NotNull(groups = {SaveCheck.class,UpdateCheck.class})
//    @Max(value = 9999, groups = SaveCheck.class)
//    @NotNull
//    @Max(9999)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
