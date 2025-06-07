package edu.fra.uas.peregos;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import edu.fra.uas.peregos.model.PeregosStudent;

@Repository
public class PeregosRepository extends HashMap<Integer, PeregosStudent> {

}
