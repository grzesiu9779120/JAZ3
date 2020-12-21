package pl.edu.pjwstk.jaz.zad1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class AverageController {
    @GetMapping("average")
    public AverageResult getAverage(@RequestParam(value = "numbers", required = false) String numbers) {
        double wynik = 0;
        String[] arrayOfString;

        if (numbers == null || numbers.equals("")) {
            return new AverageResult("Please put parameters.");
        }
        arrayOfString = numbers.split(",");
        for (String s : arrayOfString) {
            wynik += Integer.parseInt(s);
        }

        BigDecimal result = new BigDecimal(wynik/arrayOfString.length);
        result = result.setScale(2,RoundingMode.HALF_UP).stripTrailingZeros();

        return new AverageResult("Average equals: " + result);
    }

}
