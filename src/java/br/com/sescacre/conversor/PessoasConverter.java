/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sescacre.conversor;

import br.com.sescacre.dao.PessoasDAO;
import br.com.sescacre.entidades.Pessoas;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Rennan Francisco
 */
@FacesConverter(value = "pessoaConverter")
public class PessoasConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            PessoasDAO pd = new PessoasDAO();
            Pessoas p = pd.pesquisaPorId(Integer.parseInt(value));
            return p;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof Pessoas){
            Pessoas p = (Pessoas) value;
            return String.valueOf(p.getIdPessoa());
        }
        return "";
    }
}
