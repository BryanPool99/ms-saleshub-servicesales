package com.saleshub.ms.servicesales.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

  BAD_PARAMETERS("SCI-001", ErrorCategoryEnum.INVALID_REQUEST,
      "Parámetros ingresados no existen o son incorrectos"),
  USER_ALREADY_EXISTS("SCI-002", ErrorCategoryEnum.INVALID_REQUEST, "El usuario ya existe"),
  MODULE_BY_ID_NOT_FOUND("SCI-003", ErrorCategoryEnum.INVALID_REQUEST,
      "No se encontró el módulo con el id ingresado"),
  OPTION_BY_ID_NOT_FOUND_FOR_MODULE_ID("SCI-004", ErrorCategoryEnum.INVALID_REQUEST,
      "No se encontró el optionId ingresado para el modulo correspondiente"),
  PROFILE_BY_ID_NOT_FOUND("SCI-005", ErrorCategoryEnum.INVALID_REQUEST,
      "No se encontró el perfil con el id ingresado"),
  USER_NOT_FOUND("SCI-006", ErrorCategoryEnum.INVALID_REQUEST,
      "No se encontró el usuario con el username ingresado"),
  USER_BY_ID_NOT_FOUND("SCI-007", ErrorCategoryEnum.INVALID_REQUEST,
      "No se encontró el usuario con el id ingresado"),
  PROFILE_MODULE_NOT_FOUND("SCI-008", ErrorCategoryEnum.INVALID_REQUEST,
      "No se encontró la entidad profileModule con el optionId y moduleId ingresados"),
  MODULE_ID_NOT_PROVIDED("SCI-009", ErrorCategoryEnum.INVALID_REQUEST,
      "El campo moduleId es obligatorio y no fue proporcionado en la solicitud"),
  PROFILE_ID_NOT_EXIST("SCI-010", ErrorCategoryEnum.INVALID_REQUEST,
      "El profile Id  proporcionado no existe en la tabla profile");

  private final String code;
  private final ErrorCategoryEnum category;
  private final String detail;
}
