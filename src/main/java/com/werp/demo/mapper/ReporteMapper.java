package com.werp.demo.mapper;

import com.werp.demo.dto.response.ReporteResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReporteMapper {

    List<ReporteResponse> toResponseList(List<ReporteResponse> response);
}
