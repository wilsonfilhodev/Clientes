package com.will.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.will.model.Estado;
import com.will.utils.ControllerMap;
import com.will.utils.ControllerPath;

@RestController
@RequestMapping(path = ControllerPath.ESTADO_PATH)
public class EstadoController extends GenericController<Estado, Long> implements ControllerMap {

}

