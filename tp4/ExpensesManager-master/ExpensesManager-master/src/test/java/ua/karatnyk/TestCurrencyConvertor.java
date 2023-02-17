package ua.karatnyk;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ua.karatnyk.impl.CurrencyConversion;
import ua.karatnyk.impl.CurrencyConvertor;
import ua.karatnyk.impl.OfflineJsonWorker;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class TestCurrencyConvertor {

    // use OfflineJsonWorker to get the CurrencyConversion object
    // private CurrencyConversion conversion;

    // Boite noire
    // se qui doit etre tester
    // Convertir des montants uniquement entre les devises suivantes : USD, CAD,
    // GBP, EUR, CHF, INR, AUD
    // Seulement aconversionepter des montants entre [0, 10000]

    private CurrencyConversion conversion;

    private Map<String, TestFacilitator[]> dataSet = new HashMap<String, TestFacilitator[]>();

    private String[] invalidCurrencyList = { "JPY", "DZD", "MAD", "NTD" };

    private String[] validCurrencyList = { "USD", "CAD", "GBP", "EUR", "CHF", "INR", "AUD" };

    @Before
    public void before() {
        OfflineJsonWorker manager = new OfflineJsonWorker();
        conversion = manager.parser();
    }

    @Before
    public void init() {
        this.conversion = new OfflineJsonWorker().parser();
    }

    @Before
    public void initUSDSet() {
        final TestFacilitator[] t = {
                new TestFacilitator("CAD", 0, 0),
                new TestFacilitator("CAD", 10.0, 13.443506376863663),
                new TestFacilitator("GBP", 5000, 4230.051311689225),
                new TestFacilitator("EUR", 9999, 9761.521700080444),
                new TestFacilitator("CHF", 2034, 1950.841201255848),
                new TestFacilitator("INR", 7500, 613047.2124163355),
                new TestFacilitator("AUD", 9054, 13714.070627767669),
        };
        dataSet.put("USD", t);
    }

    @Before
    public void initCadDataSet() {
        final TestFacilitator[] t = {
                new TestFacilitator("CAD", 0, 0),
                new TestFacilitator("USD", 20.0, 14.87707108498129),
                new TestFacilitator("GBP", 5701, 3587.6834289963517),
                new TestFacilitator("EUR", 9954, 7228.464201891572),
                new TestFacilitator("CHF", 2001, 1427.5965893907003),
                new TestFacilitator("INR", 7365, 447809.03561656165),
                new TestFacilitator("AUD", 8654, 9750.574949747866),
        };
        dataSet.put("CAD", t);
    }

    @Before
    public void initGbpDataSet() {
        final TestFacilitator[] t = {
                new TestFacilitator("USD", 0, 0),
                new TestFacilitator("USD", 1.0, 1.182018758539197),
                new TestFacilitator("CAD", 1250, 1986.3095897492708),
                new TestFacilitator("EUR", 2500, 2884.8639267383037),
                new TestFacilitator("CHF", 5000, 5668.46335992024),
                new TestFacilitator("INR", 7500, 724633.3049462723),
                new TestFacilitator("AUD", 10000, 17904.007883756138),
        };
        dataSet.put("GBP", t);
    }

    @Before
    public void initEurDataSet() {
        final TestFacilitator[] t = {
                new TestFacilitator("GBP", 0, 0),
                new TestFacilitator("USD", 1.0, 1.024328),
                new TestFacilitator("CAD", 1250, 1721.3200000000002),
                new TestFacilitator("GBP", 2500, 2166.48),
                new TestFacilitator("CHF", 5000, 4912.245),
                new TestFacilitator("INR", 7500, 627961.4249999999),
                new TestFacilitator("AUD", 10000, 15515.47),
        };
        dataSet.put("EUR", t);
    }

    @Before
    public void initChfDataSet() {
        final TestFacilitator[] t = {
                new TestFacilitator("GBP", 0, 0),
                new TestFacilitator("USD", 1.0, 1.0426271490937442),
                new TestFacilitator("CAD", 1250, 1752.0705909416165),
                new TestFacilitator("GBP", 2500, 2205.183169813395),
                new TestFacilitator("EUR", 5000, 5089.3227027560715),
                new TestFacilitator("INR", 7500, 639179.6673415108),
                new TestFacilitator("AUD", 10000, 15792.64674298615),
        };
        dataSet.put("CHF", t);
    }

    @Before
    public void initINRDataSet() {
        final TestFacilitator[] t = {
                new TestFacilitator("CHF", 0, 0),
                new TestFacilitator("USD", 1.0, 0.012233968033944123),
                new TestFacilitator("CAD", 1250, 20.558428409834253),
                new TestFacilitator("GBP", 2500, 25.87515626457469),
                new TestFacilitator("EUR", 5000, 59.71704392511053),
                new TestFacilitator("CHF", 7500, 88.00342513077138),
                new TestFacilitator("AUD", 10000, 185.30760070174694),
        };
        dataSet.put("INR", t);
    }

    @Before
    public void initAUDDataSet() {
        final TestFacilitator[] t = {
                new TestFacilitator("EUR", 0, 0),
                new TestFacilitator("USD", 1.0, 0.660197854141705),
                new TestFacilitator("CAD", 1250, 1109.4217577682145),
                new TestFacilitator("GBP", 2500, 1396.3353994432653),
                new TestFacilitator("EUR", 5000, 3222.590098785277),
                new TestFacilitator("INR", 7500, 404732.4541248187),
                new TestFacilitator("CHF", 10000, 6332.060839922993),
        };
        dataSet.put("AUD", t);
    }

    @Before
    public void initOutsideInterval() {

    }

    @Test
    public void allCases() {
        for (String key : dataSet.keySet()) {
            final TestFacilitator[] t = dataSet.get(key);
            for (TestFacilitator facilitator : t) {
                double output = 0.0;
                try {
                    output = CurrencyConvertor.convert(facilitator.getInitialValue(), key, facilitator.getTarget(),
                            conversion);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                boolean assertion = output == facilitator.getTargetValue();
                if (!assertion) {
                    System.out.println("Error converting " + facilitator.getInitialValue() + " " + key + " to "
                            + facilitator.getTarget());
                    System.out.println("Expected " + output + " but received " + facilitator.getTargetValue());
                }
                assertTrue(assertion);
            }
        }
    }

    @Test(expected = ParseException.class)
    public void initWrongCurrencyTest() throws ParseException {
        for (String from : this.invalidCurrencyList) {
            for (String to : this.validCurrencyList) {
                CurrencyConvertor.convert(500, from, to, this.conversion);
            }
        }
    }

    @Test(expected = ParseException.class)
    public void initToWrongCurrency() throws ParseException {
        for (String to : this.validCurrencyList) {
            for (String from : this.invalidCurrencyList) {
                CurrencyConvertor.convert(500, from, to, this.conversion);
            }
        }
    }

    @Test(expected = Exception.class)
    public void limitCasesPos() throws ParseException {
        for (String from : this.validCurrencyList) {
            for (String to : this.validCurrencyList) {
                if (!to.equals(from)) {
                    CurrencyConvertor.convert(100001, from, to, this.conversion);
                    CurrencyConvertor.convert(100001, to, from, this.conversion);
                }
            }
        }
    }

    @Test(expected = Exception.class)
    public void limitCasesNeg() throws ParseException {
        for (String from : this.validCurrencyList) {
            for (String to : this.validCurrencyList) {
                if (!to.equals(from)) {
                    CurrencyConvertor.convert(-1, from, to, this.conversion);
                    CurrencyConvertor.convert(-1, to, from, this.conversion);
                }
            }
        }
    }

    private class TestFacilitator {
        private String target;
        private double initialValue;
        private double targetValue;

        public TestFacilitator(String target, double initialValue, double targetValue) {
            this.target = target;
            this.initialValue = initialValue;
            this.targetValue = targetValue;
        }

        public double getInitialValue() {
            return initialValue;
        }

        public double getTargetValue() {
            return targetValue;
        }

        public String getTarget() {
            return target;
        }

        public void setInitialValue(double initialValue) {
            this.initialValue = initialValue;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public void setTargetValue(double targetValue) {
            this.targetValue = targetValue;
        }
    }

    // #################################################################################
    // #################################################################################
    // #################################################################################
    // #################################################################################

    // #################################################################################
    // #################################################################################
    // #################################################################################
    // #################################################################################

    // Boite blanche
    // Testez la méthode convert() en utilisant les 5 critères de sélection de jeux
    // de test quand il fait du sens : couverture
    // des instructions, couverture des arcs du graphe de flot de contrôle,
    // couverture des chemins indépendants du graphe
    // de flot de contrôle, couverture des conditions, couverture des i-chemins.

    // couverture des instructions
    @Test
    public void insideIf() {
        try {
            CurrencyConvertor.convert(2500, "CAD", "", conversion);

        } catch (Exception e) {
            System.out.println("if this message appears, all's good it's as expected 1");
        }
    }

    @Test
    public void notInsideIf() {

        try {
            CurrencyConvertor.convert(5000, "CAD", "AUD", conversion);

        } catch (Exception e) {
            System.out.println("not supposed to come here. Something's wrong");
        }
    }

    // couverture des arcs du graphe de flot de contrôle
    // Le graphe de flot de controle gcf se
    // trouve dans le dossier GraphUtiles du tp
    // Ift3913_Tp2\tp4\graphsUtiles
    // les testes exactement pareil que pour ceux des instructions
    // car on parcoure tous les arcs avec les test utilisé plus haut
    // Je met quand meme les memes testes pour "au cas ou" il
    // faut en faire anyway pour l'evaluation du tp
    @Test
    public void insideIf2() {
        try {
            CurrencyConvertor.convert(5000, "", "", conversion);

        } catch (Exception e) {
            System.out.println("if this message appears, all's good it's as expected 2");
        }
    }

    @Test
    public void notInsideIf2() {
        try {
            CurrencyConvertor.convert(5555, "CAD", "EUR", conversion);

        } catch (Exception e) {
            fail("not supposed to come here. Something's wrong");
        }
    }

    // couverture des chemins indépendants du graphe de flot de contrôle
    // chemain inndependants selon graphe de flot de controle gcf se
    // trouvant dans le dossier GraphUtiles du tp
    // Ift3913_Tp2\tp4\graphsUtiles
    // 1 - 2
    // 1 - 3 - 4 - 5
    // Donc on pourrait utiliser exactement les memes tests que pour la couverture
    // des instructions et ceux des arcs (car aucune boucle),
    // mais j'en met de nouveau au cas ou on est
    // supposer
    // en mettre "de toute facon" pour l'evaluation (encore une fois)

    @Test
    public void indepPath_1_2() {
        try {
            CurrencyConvertor.convert(1111, "", "EUR", conversion);

        } catch (Exception e) {
            System.out.println("if this message appears, all's good it's as expected 3");
        }
    }

    @Test
    public void indepPath_1_3_4_5() {
        try {
            CurrencyConvertor.convert(4321, "EUR", "INR", conversion);

        } catch (Exception e) {
            fail("not supposed to come here. Something's wrong");
        }
    }

    // couverture des conditions

    // toutes les valeurs sont bien definis donc
    // if(!Vrai || !Vrai) = if(Faux || Faux)
    @Test
    public void allFalse() {
        try {
            CurrencyConvertor.convert(2022, "EUR", "USD", conversion);
        } catch (Exception e) {
            fail("something's wrong, not supposed to come here");
        }
    }

    // 1 valeur bien definis (ici From) donc
    // if(!Vrai || !Faux) = if(Faux || Vrai)
    @Test
    public void from_TrueFalse() {
        try {
            CurrencyConvertor.convert(2023, "CAD", "", conversion);

        } catch (Exception e) {
            System.out.println("if this message appears, all's good it's as expected 4");
        }
    }

    // 1 valeur bien definis (ici To) donc
    // if(!Faux || !Vrai) = if(Vrai || Faux)
    @Test
    public void to_falseTrue() {
        try {
            CurrencyConvertor.convert(2024, "", "AUD", conversion);
        } catch (Exception e) {
            System.out.println("if this message appears, all's good it's as expected 5");
        }
    }

    // toutes les valeurs sont mal definis donc
    // if(!Faux || !Faux) = if(Vrai || Vrai)
    @Test
    public void allTrue() {
        try {
            CurrencyConvertor.convert(2025, "HAPPY", "HOLYDAYS", conversion);
        } catch (Exception e) {
            System.out.println("if this message appears, all's good it's as expected 6");
        }
    }

    // couverture des i-chemins
    // Rien a faire ici car aucune boucle
    // les tests precedent coucle PLUS QU'ASSEZ touts les cas
    // de couverture des test a boites blanches
}
