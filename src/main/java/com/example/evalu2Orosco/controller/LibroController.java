/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.evalu2Orosco.controller;

import com.example.evalu2Orosco.entity.Autor;
import com.example.evalu2Orosco.entity.Editorial;
import com.example.evalu2Orosco.entity.Libro;
import com.example.evalu2Orosco.repository.AutorRepository;
import com.example.evalu2Orosco.repository.EditorialRepository;
import com.example.evalu2Orosco.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibroController {
    @Autowired
    private AutorRepository autorRepository;
     @Autowired
    private EditorialRepository editorialRepository;
    @Autowired
    private LibroRepository libroRepository;
    
    @RequestMapping("/")
    public String index(Model model) {
       
        return "index";   
}

    @RequestMapping("/libros")
    public String libros(Model model) {
        model.addAttribute("libros", libroRepository.findAll());
        return "libros";   
}
    @RequestMapping("/form")
    public String create(Model model) {
        model.addAttribute("auts", autorRepository.findAll());
        model.addAttribute("edts", editorialRepository.findAll());
        return "add";
    }
    @RequestMapping("/add")
    public String guardar(@RequestParam String titulo,@RequestParam int idaut, @RequestParam int idedt, Model model) {
        Libro lib = new Libro();
        
        lib.setTitulo(titulo);
        lib.setIdautor(idaut);
        lib.setIdeditorial(idedt);
        libroRepository.insert(lib);
        return "redirect:/libros";
    }
    @RequestMapping("/del/{id}")
    public String delete(@PathVariable(value = "id") int id) {
    
        libroRepository.deleteById(id);
        return "redirect:/libros";
    }
     @RequestMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("auts", autorRepository.findAll());
         model.addAttribute("edts", editorialRepository.findAll());
        model.addAttribute("lib", libroRepository.readLibro(id));
        return "edit";
    }
    @RequestMapping("/update")
    public String update(@RequestParam int idlibro, @RequestParam String titulo, @RequestParam int autor, @RequestParam int editorial) {
        System.out.println(idlibro+"/"+titulo+"/"+autor+"/"+editorial);
       Libro lib = libroRepository.readLibro(idlibro);
        
        lib.setTitulo(titulo);
        lib.setIdautor(autor);
         lib.setIdeditorial(editorial);
       libroRepository.update(lib);
        return "redirect:/libros";
    }
}

