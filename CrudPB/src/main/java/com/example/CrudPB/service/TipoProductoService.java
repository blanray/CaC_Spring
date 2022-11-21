package com.example.CrudPB.service;

import com.example.CrudPB.dto.response.TipoProductoDto;
import com.example.CrudPB.entities.TipoProducto;
import com.example.CrudPB.exceptions.RecordNotFoundException;
import com.example.CrudPB.repository.ITipoProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        ObjectMapper mapper = new ObjectMapper();
        List<TipoProducto> tipoProductos = tipoProductoRepository.findAll();
        List<TipoProductoDto> tipoProductoDtos = tipoProductos.stream().map(tPrd -> {
            return new TipoProductoDto(tPrd.getTip_id(), tPrd.getTip_descripcion());
        }).collect(Collectors.toList());
        return tipoProductoDtos;
    }

    @Override
    public TipoProductoDto crearTipoProducto(TipoProductoDto tipoProductoDto){
        TipoProducto miTemporal = new TipoProducto(tipoProductoDto.getTip_id(), tipoProductoDto.getTip_descripcion());
        TipoProducto miTemp = tipoProductoRepository.save(miTemporal);
        TipoProductoDto miElementoCreado = new TipoProductoDto(miTemp.getTip_id(), miTemp.getTip_descripcion());
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


}
