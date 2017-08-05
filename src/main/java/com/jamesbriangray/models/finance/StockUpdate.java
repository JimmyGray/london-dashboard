package com.jamesbriangray.models.finance;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class StockUpdate implements Serializable {
    private final String name;
    private final BigDecimal price;
    private final BigDecimal open;
    private final BigDecimal prevClose;
    private final BigDecimal change;
    private final String lastUpdated = LocalDateTime.now(ZoneId.of("GMT")).toString();;
}
