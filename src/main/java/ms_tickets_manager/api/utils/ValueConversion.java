package ms_tickets_manager.api.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Component
public class ValueConversion {

    public BigDecimal DataValueConversionBigDecimal(String value){
        value = value.replace("R$", "")
                .replace("$", "")
                .replace(".", "")
                .replace(",", ".")
                .trim();
        return new BigDecimal(value);
    }

    public String DataValueConversionString(BigDecimal value){
        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formato.format(value);
    }
}
