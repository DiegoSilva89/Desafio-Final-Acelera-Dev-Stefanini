package com.stefanini.api.excel;

import com.stefanini.api.venda.Venda;
import com.stefanini.api.venda.VendaRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {

    private final VendaRepository vendaRepository;

    @Autowired
    public ExcelService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public void processarPlanilha(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0); //Assumindo que o arquivo tem apenas uma planilha

            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); //Ignorar cabeçalho

            List<Venda> vendas = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                VendaExcelDTO vendaExcelDTO = extrairDadosVenda(row);
                Venda venda = converterParaEntidade(vendaExcelDTO);
                vendas.add(venda);
            }

            vendaRepository.saveAll(vendas);
        }
    }

    private VendaExcelDTO extrairDadosVenda(Row row) {
        Long codigoProduto = (long) row.getCell(0).getNumericCellValue();
        String cpfUsuario = row.getCell(1).getStringCellValue();
        int quantidadeComprada = (int) row.getCell(2).getNumericCellValue();
        LocalDate dataCompra = row.getCell(3).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return new VendaExcelDTO(codigoProduto, cpfUsuario, quantidadeComprada, dataCompra);
    }

    private Venda converterParaEntidade(VendaExcelDTO vendaExcelDTO) {
        Venda venda = new Venda(
                vendaExcelDTO.getCodigoProduto(),
                vendaExcelDTO.getCpfUsuario(),
                vendaExcelDTO.getQuantidadeComprada(),
                vendaExcelDTO.getDataCompra()
        );
        venda.setPrecoTotal(BigDecimal.ZERO); //Definir preço total conforme regra de negócio
        return venda;
    }
}
