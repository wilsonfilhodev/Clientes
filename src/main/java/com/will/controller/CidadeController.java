package com.will.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.will.model.Cidade;
import com.will.utils.ControllerMap;
import com.will.utils.ControllerPath;

@RestController
@RequestMapping(path = ControllerPath.CIDADE_PATH)
public class CidadeController extends GenericController<Cidade, Long> implements ControllerMap {

}

