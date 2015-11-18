package com.epam.ag;

import com.epam.ag.entity.Aircraft;
import com.epam.ag.entity.Plane;
import com.epam.ag.entity.Properties;
import com.epam.ag.entity.Property;
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

        Aircraft plane = new Plane();

//        plane.setModel("SU-9");
//        plane.setOrigin("USSR");
//
//        plane.setCharacteristic("type", "патрульный");
//        plane.setCharacteristic("seats", 1);
//        plane.setCharacteristic("weapons", false);
//        plane.setCharacteristic("missiles", 0);
//        plane.setCharacteristic("hasRadar", false);



        // TEST 1
//        Property p = new Property<Integer>(5);
//        int w = p.getValue();
//
//        System.out.println( w);



        // TEST 2
        Properties pp = new Properties();
        pp.set("par2", "test");
        pp.set("par1", 5);
        pp.set("bb-test", true);
        pp.set("d-test", 2.56);

        int x = pp.get("par1", Integer.class);
        String s = pp.get("par2", String.class);
        boolean b = pp.get("bb-test", Boolean.class);
        double d = pp.get("d-test", Double.class);

        System.out.println("X = " + x);
        System.out.println("S = " + s);
        System.out.println("B = " + b);
        System.out.println("D = " + d);







        // Надо чтобы работало :(
        //System.out.println( plane.getChar() );

        //String  a1 = plane.get("type", String.class);
        //int     a2 = plane.get("seats", Integer.class);
        // boolean a3 = plane.get("weapons", Boolean.class);
//        int     a4 = plane.get("missiles", Integer.class);
//        boolean a5 = plane.get("hasRadar", Boolean.class);

        /*
        Property<Integer> p1 = new Property<>(2);
        Property<Integer> p2 = new Property<>(3);
        Property<String> p3 = new Property<>("String");
        Property<Boolean> p4 = new Property<>(true);
        Property<Double> p5 = new Property<>(4.56);

        int t1 = p1.getValue();
        int t2 = p2.getValue();
        String t3 = p3.getValue();
        Boolean t4 = p4.getValue();
        Double t5 = p5.getValue();

        System.out.println( t1 + t2 );*/



        /*
        System.out.println( p1.getValue().getClass() );
        System.out.println( p1.getValue() );
        */
        //int tt = p1.getValue() + p2.getValue();




        /*
        plans.setParameters("length", 9.14);
        plans.setParameters("width", 13.50);
        plans.setParameters("height", 9.14);
        */

        //int y = 2 + (Integer) plane.setCharacteristic("seats");


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
