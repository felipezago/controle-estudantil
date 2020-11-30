package Model;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;

public class Atividades {
    private String titulo;
    private String desc;
    private String data;
    private int id;
    private String status;
    private Materia materia;
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Materia getMateria() {
        return materia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return this.data;
        
    }

    

}
