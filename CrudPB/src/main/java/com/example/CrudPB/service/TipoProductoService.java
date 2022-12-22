package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.SuccessDto;
import com.example.CrudPB.dto.response.TipoProductoDto;
import com.example.CrudPB.entities.Producto;
import com.example.CrudPB.entities.TipoProducto;
import com.example.CrudPB.exceptions.RecordNotFoundException;
import com.example.CrudPB.repository.ITipoProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoProductoService implements ITipoProductoService {

    private ITipoProductoRepository tipoProductoRepository;

    public TipoProductoService(ITipoProductoRepository tipoProductoRepository) {
        this.tipoProductoRepository = tipoProductoRepository;
    }

    @Override
    public List<TipoProductoDto> listarTipoProductos() {
           List<TipoProducto> tipoProductos = tipoProductoRepository.findAll();

        if (tipoProductos.size()<1) {
            TipoProducto temp = new TipoProducto(0, "La lista está vacia");
            tipoProductos.add(temp);
        }

        List<TipoProductoDto> tipoProductoDtos = tipoProductos.stream().map(tPrd -> {
            return new TipoProductoDto(tPrd.getTip_id(), tPrd.getTip_descripcion());
        }).collect(Collectors.toList());
        return tipoProductoDtos;
    }

    @Override
    public TipoProductoDto crearTipoProducto(TipoProductoDto tipoProductoDto){
        TipoProducto miTemporal = new TipoProducto(tipoProductoDto.getTip_id(), tipoProductoDto.getTip_descripcion());
        TipoProductoDto miElementoCreado;

        try{
            TipoProducto miTemp = tipoProductoRepository.save(miTemporal);
            miElementoCreado = new TipoProductoDto(miTemp.getTip_id(), miTemp.getTip_descripcion());
        }
        catch(DataAccessException e){
            miElementoCreado = new TipoProductoDto(0,"Error, no se pudo crear el Tipo de Producto");
        }
        return miElementoCreado ;
    }

    @Override
    public TipoProductoDto encontrarTipoPorID(Integer idTipoProducto){
        Optional<TipoProducto> miTemp = tipoProductoRepository.findById(idTipoProducto);
        if (miTemp.isPresent()){
            TipoProductoDto miElementoCreado = new TipoProductoDto(miTemp.get().getTip_id(), miTemp.get().getTip_descripcion());
            return miElementoCreado ;
        }
        else {
            throw new RecordNotFoundException("Tipo Producto", "ID", idTipoProducto);
        }
    }
@Override
    public TipoProductoDto actualizarTipoPorID(Integer idTipoProducto, TipoProductoDto tipoProductoDto){
        TipoProducto miTemp = tipoProductoRepository.findById(idTipoProducto).orElseThrow(
                ()-> new RecordNotFoundException("Tipo Producto", "ID", idTipoProducto));

        miTemp.setTip_descripcion(tipoProductoDto.getTip_descripcion());
        tipoProductoRepository.save(miTemp);

        TipoProductoDto miRegistroActualizado = new TipoProductoDto(miTemp.getTip_id(), miTemp.getTip_descripcion());
        return miRegistroActualizado;
}

    public SuccessDto borrarTipoPorID(Integer idTipoProducto){

        Optional<TipoProducto> miTemp = tipoProductoRepository.findById(idTipoProducto);

        if (miTemp.isPresent()){

            try{
            tipoProductoRepository.deleteById(idTipoProducto);
            String respuesta = String.format("El Tipo de Producto con ID %s fue borrado exitosamente", idTipoProducto);
            SuccessDto miRespuestaTemp = new SuccessDto(respuesta);
            return miRespuestaTemp ;}
            catch (DataIntegrityViolationException e){
                String respuesta = String.format("El Tipo de Producto con ID %s no puede ser borrado por contener Productos asociados", idTipoProducto);
                SuccessDto miRespuestaTemp = new SuccessDto(respuesta);
                return miRespuestaTemp ;
            }
        }
        else {
            throw new RecordNotFoundException("Tipo Producto", "ID", idTipoProducto);
        }

    }

    @Override
    public SuccessDto borrarAllTipos(){

        try{
            tipoProductoRepository.deleteAll();
            String respuesta = "Se ha vaciado exitosamente la lista de Tipos de Producto";
            SuccessDto miRespuestaTemp = new SuccessDto(respuesta);
            return miRespuestaTemp;
        }
        catch (DataIntegrityViolationException e){
            String respuesta = "Se produjo un error vaciando la lista de Producto, compruebe que no existan Productos asociados";
            SuccessDto miRespuestaTemp = new SuccessDto(respuesta);
            return miRespuestaTemp ;
        }
    }

}
