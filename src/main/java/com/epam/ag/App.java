package com.epam.ag;

import com.epam.ag.entity.Aircraft;
import com.epam.ag.importer.AircraftSAXParser;
import com.epam.ag.importer.ImportFactory;
import org.slf4j.Logger;

import java.io.File;
import java.util.List;

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
        File planesXmlFile = new File(classLoader.getResource("xml/planes.xml").getFile());

        if (planesXmlFile.exists()) {
            log.trace("XML file found `{}`", planesXmlFile.getName());

            // Import from XML to class
            List<Aircraft> planes_sax = ImportFactory.importFromXML(planesXmlFile, AircraftSAXParser.class);
            // List<Aircraft> planes_stax = ImportFactory.importFromXML(planesXmlFile, AircraftSTASParser.class);
            // List<Aircraft> planes_dom = ImportFactory.importFromXML(planesXmlFile, AircraftDOMParser.class);}

            // Show result
            if (!planes_sax.isEmpty()) {
                for(Aircraft aircraft : planes_sax) {
                    System.out.println( aircraft.toString() );
                }
            }
        }
    }
}
