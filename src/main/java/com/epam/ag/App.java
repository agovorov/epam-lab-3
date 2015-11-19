package com.epam.ag;

import com.epam.ag.entity.Plane;
import com.epam.ag.entity.Planes;
import com.epam.ag.exporter.EntityExporter;
import com.epam.ag.exporter.JaxBExporter;
import org.slf4j.Logger;

import java.io.InputStream;

/**
 * @author Govorov Andrey
 *         1.	Создать файл XML и соответствующую ему схему XSD.
 *         2.	При разработке XSD использовать простые и комплексные типы, перечисления, шаблоны и предельные значения,
 *         обязательно использование атрибутов и типа ID.
 *         3.	Сгенерировать (создать) Java-класс, соответствующий данному описанию.
 *         4.	Создать Java-приложение для разбора XML-документа и инициализации коллекции объектов информацией из XML-файла.
 *         Для разбора использовать SAX, DOM и StAX парсеры. Для сортировки объектов использовать интерфейс Comparator.
 *         5.	Произвести проверку XML-документа с привлечением XSD.
 *         6.	Определить метод, производящий преобразование разработанного XML-документа в документ, указанный в каждом задании.
 *         <p/>
 *         <p/>
 *         Personal task
 *         8.	Военные самолеты.
 *         Военные самолеты можно описать по следующей схеме:
 *         •	Model – название модели.
 *         •	Origin – страна производства.
 *         •	Chars (должно быть несколько) – характеристики, могут быть следующими: тип (самолет поддержки,
 *         сопровождения, истребитель, перехватчик, разведчик), кол-во мест (1 либо 2), боекомплект (есть либо нет [разведчик],
 *         если есть, то: ракеты [0 – 10]), наличие радара.
 *         •	Parameters – длина (в метрах), ширина (в метрах), высота (в метрах).
 *         •	Price – цена (в талерах).
 *         Корневой элемент назвать Plane.
 */
public class App {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.trace("Loading xml file");
        ClassLoader classLoader = App.class.getClassLoader();
        InputStream xmlStream = classLoader.getResourceAsStream("xml/planes.xml");


        // Список самолетов
        Planes planes = new Planes();

        // First plane
        Plane plane = new Plane();
        plane.setModel("SU-9");
        plane.setOrigin("USSR");

        plane.setCharacteristic("type", "патрульный");
        plane.setCharacteristic("seats", 1);
        plane.setCharacteristic("weapons", false);
        plane.setCharacteristic("missiles", 0);
        plane.setCharacteristic("hasRadar", true);

        plane.setParameter("length", 9.14);
        plane.setParameter("width", 13.50);
        plane.setParameter("height", 2.85);

        plane.setPrice("amount", 123456.34);
        plane.setPrice("currency", "Талер");
        planes.add(plane);

        // Test plane 2
        /*Plane plane2 = new Plane();
        plane2.setModel("SU-12");
        plane2.setOrigin("USSR");

        plane2.setCharacteristic("type", "исптребитель");
        plane2.setCharacteristic("seats", 2);
        plane2.setCharacteristic("weapons", true);
        plane2.setCharacteristic("missiles", 10);
        plane2.setCharacteristic("hasRadar", true);

        plane2.setParameter("length", 9.14);
        plane2.setParameter("width", 13.50);
        plane2.setParameter("height", 2.85);

        plane2.setPrice("amount", 544432.34);
        plane2.setPrice("currency", "Талер");
        */
        //planes.add(plane2);


        EntityExporter exporter = new JaxBExporter();
        if (!exporter.export(planes, "out.xml")) {
            log.info("Unable to save data to xml");
        } else {
            log.info("Data successfully saved to xml!");
        }

        //List<Aircraft> planeList = ImportFactory.importFromXML(xmlStream, AircraftSAXParser.class);
        //List<Aircraft> planeList = ImportFactory.importFromXML(xmlStream, AircraftSTAXParser.class);
        //List<Aircraft> planeList = ImportFactory.importFromXML(xmlStream, AircraftDOMParser.class);

        /*
        if (!planeList.isEmpty()) {
            log.trace("Show result of parsing");
            for (Aircraft aircraft : planeList) {
                System.out.println(aircraft.toString());
            }

            // We need a wrapper to JAXB
            Planes planesWrapper = new Planes(planeList);

            // Save to XML
            EntityExporter exporter = new JaxBExporter();
            if (!exporter.export(planesWrapper, "out.xml")) {
                log.info("Unable to save data to xml");
            } else {
                log.info("Data successfully saved to xml!");
            }
        } else {
            log.trace("No returned data");
        }*/
    }
}
