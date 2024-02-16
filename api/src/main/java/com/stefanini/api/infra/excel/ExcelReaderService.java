package com.stefanini.api.infra.excel;

import com.stefanini.api.venda.Venda;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelReaderService {

    public List<Venda> lerPlanilha(InputStream inputStream) throws IOException {
        List<Venda> vendas = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0); //Assumindo que os dados estão na primeira planilha

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); //Ignorar o cabeçalho

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            //Ler os dados de cada célula da linha
            long codigoProduto = (long) row.getCell(0).getNumericCellValue();
            String cpfUsuario = row.getCell(1).getStringCellValue();
            int quantidadeProdutos = (int) row.getCell(2).getNumericCellValue();
            String dataCompraStr = row.getCell(3).getStringCellValue();

            //Converter a String da data para um objeto LocalDate usando o formato esperado
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dataCompra = LocalDate.parse(dataCompraStr, formatter);

            //Criar uma nova instância de Venda usando o construtor existente e adicioná-la à lista de vendas
            Venda venda = new Venda(codigoProduto, cpfUsuario, quantidadeProdutos, dataCompra);
            vendas.add(venda);
        }

        workbook.close();
        return vendas;
    }
}
