/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.evalu2Orosco.repository;

import com.example.evalu2Orosco.entity.Autor;
import com.example.evalu2Orosco.entity.Editorial;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EditorialRepository {
    @Select("select * from editorial")
    public List<Editorial> findAll();
    
    
}
