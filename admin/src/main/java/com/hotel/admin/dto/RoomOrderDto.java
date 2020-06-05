package com.hotel.admin.dto;

import com.hotel.model.RoomOrder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xu
 */
@Data
public class RoomOrderDto extends RoomOrder {
	BigDecimal totalPayPrice;
}
