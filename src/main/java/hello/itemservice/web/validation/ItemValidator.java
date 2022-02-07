package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
        
        if(!StringUtils.hasText(item.getItemName())){
//            errors.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
            errors.rejectValue("itemName","required");
        }
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "required");

        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
//            errors.addError(new FieldError("item","price",item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, null));
            errors.rejectValue("price","range", new Object[]{1000,10000},null);
        }
        if(item.getQuantity() == null || item.getQuantity() > 10000){
//            errors.addError(new FieldError("item","quantity", item.getQuantity(), false, new String[]{"max.item.quantity"},new Object[]{9999},null));
            errors.rejectValue("quantity","max", new Object[]{9999},null);
        }

//        특정 필드가 아닌 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
//                errors.put("globalError","가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
//                errors.addError(new ObjectError("item",new String[]{"totalPriceMin"},new Object[]{10000,resultPrice},null));
                errors.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
            }
        }
    }
}
