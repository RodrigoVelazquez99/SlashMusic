package com.ivrotef.slashmusic.controller;

import com.ivrotef.slashmusic.model.Cancion;
import com.ivrotef.slashmusic.model.Persona;
import com.ivrotef.slashmusic.config.PersonaWrapper;
import com.ivrotef.slashmusic.controller.CancionService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/")
public class CancionController {

  @Autowired
  CancionService cancionService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView verInicio(@AuthenticationPrincipal PersonaWrapper persona) {
    ModelAndView modelAndView = new ModelAndView("index");
    String correo = persona.getPersona().getCorreo();
    modelAndView.addObject("currentUsername", correo);
    modelAndView.addObject("canciones", cancionService.getCanciones());
    return modelAndView;
  }

}