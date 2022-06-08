package com.example.cookmaster.ui.receptes;

import androidx.annotation.NonNull;

import com.example.cookmaster.ui.classes.Receptes;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorReceptes implements Serializable {
    private HashMap<String,Receptes> llistaNom;
    private HashMap<String,Receptes> llistaId;
    public GestorReceptes(){
        llistaNom = new HashMap<>();
        llistaId = new HashMap<>();
        //inici();
    }

    public void setReceptes(ArrayList<Receptes> list){

        for (Receptes r : list){
            llistaNom.put(r.getNom(), r);
            llistaId.put(r.getId(), r);
        }

    }

    public void set(HashMap<String, Receptes> map){
        ArrayList<Receptes> temp = new ArrayList<>(map.values());
        if(map.containsKey(temp.get(0).getId())){
            llistaId = map;

            for(Receptes r : temp){
                llistaNom.put(r.getNom(), r);
            }
        }else if(map.containsKey(temp.get(0).getNom())){
            llistaNom = map;

            for(Receptes r : temp){
                llistaId.put(r.getId(), r);
            }
        }
    }

    public int getSize(){
        return llistaNom.size();
    }

    public void clear(){
        llistaNom.clear();
        llistaId.clear();
    }
    public boolean has(@NonNull Receptes recepta){
        return llistaNom.containsKey(recepta.getNom());
    }
    public boolean has(String recepta){
        return llistaNom.containsKey(recepta) || llistaId.containsKey(recepta);
    }

    public boolean add(Receptes recepta){
        if(has(recepta)){return false; }
        llistaNom.put(recepta.getNom(), recepta);
        llistaId.put(recepta.getId(), recepta);
        return true;
    }

    public void remove(@NonNull Receptes recepta){
        llistaNom.remove(recepta.getNom());
        llistaId.remove(recepta.getId());
    }
    public void remove(String recepta){
        if(llistaId.containsKey(recepta)){
            llistaNom.remove(llistaId.get(recepta).getNom());
            llistaId.remove(recepta);
        }else if(llistaNom.containsKey(recepta)){
            llistaId.remove(llistaNom.get(recepta).getId());
            llistaNom.remove(recepta);
        }
    }

    public Receptes get(String recepta){
        if(llistaNom.containsKey(recepta)){
            return llistaNom.get(recepta);
        }else if(llistaId.containsKey(recepta)){
            return llistaId.get(recepta);
        }
        return null;
    }

    public ArrayList<Receptes> getAll(){
        return new ArrayList<>(llistaNom.values());
    }


    private void inici(){

        add(new Receptes("Amanida de cigrons",

                "—70g de cigró sec o 200g de cigró cuit per persona\n" +
                "—2 tomàquets del tipus que us agradi: de la pera, raf, canari, cirerol, de branca… És millor que sigui de carn consistent, per poder tallar-lo a bocins petits sense que es desfaci\n" +
                "—1 pebrot vermell\n" +
                "—1 pebrot verd del tipus italià\n" +
                "—1 llauna de tonyina en oli d’oliva\n" +
                "—1 llauna d’olives farcides d’anxova o un grapat d’olives negres d’Aragó",

                "Renteu totes les hortalisses i eixugueu-les.\n" +
                "\n" +
                "Agafeu el pebrot vermell, obriu-lo pel mig i traieu-ne les llavors. Si el pebrot és gros, en tindreu prou amb la meitat i podeu desar el que no feu servir. El talleu a la juliana, en tires no gaire gruixudes però no gaire primes, i després talleu les tires a daus petits.\n" +
                "\n" +
                "Feu igual amb el pebrot verd. Com que acostumen a ser més petits i de polpa menys carnosa, segurament el tallareu sencer.\n" +
                "\n" +
                "Feu igual amb els tomàquets. És important que no siguin massa madurs perquè no deixin anar massa suc.\n" +
                "\n" +
                "Barregeu-ho tot amb els cigrons i afegiu-hi una mica de sal.\n" +
                "\n" +
                "Poseu-hi les olives que hàgiu triat.\n" +
                "\n" +
                "Aboqueu-hi la tonyina i poseu-hi la quantitat que vulgueu de l’oli d’oliva de la llauna, que li donarà més gust i olor.",

                "1",

                "Cal",

                FirebaseAuth.getInstance().getUid(),

                "nidea", ""
                ));
        add(new Receptes("Amanida de llenties", "400 gr de llenties\n" +
                "100 gr de pebrot vermell\n" +
                "Mitja ceba\n" +
                "1 tomàquet\n" +
                "Una o dues llaunes de tonyina\n" +
                "Una pastanaga\n" +
                "Oli d’oliva\n" +
                "Vinagre\n" +
                "Sal i pebre al gust","Primer de tot, hem de ficar a bullir les llenties, o bé deixar-les en remull durant tota la nit. Quan l’aigua comenci a bullir, afegim les llenties i les deixem durant una hora aproximadament, o fins que veiem que estan fetes." +
                "Una vegada estiguin fetes, les aboquem a un colador i deixem que corri una mica d’aigua freda pel damunt. Mentrestant, anem tallant els pebrots, la ceba, les olives, la pastanaga i el tomàquet.\n" +
                "\n" +
                "Una vegada estigui tot ben tallat i les llenties ja estiguin fredes, incorporem tots aquests ingredients juntament amb la llauna de tonyina i ho remenem bé. Afegim sal, oli i pebre al gust, i a menjar!", "2",
                "Cal",

                FirebaseAuth.getInstance().getUid(),

                "nidea", ""));
        add(new Receptes("Amanida de pasta", "200 gramos de pasta\n" +
                "1 lata de maíz\n" +
                "3 latas de atún\n" +
                "2 huevos\n" +
                "Pimiento rojo de lata\n" +
                "Canónigos\n" +
                "Sal\n" +
                "Pimienta negra\n" +
                "1 cucharada de aceite de oliva\n" +
                "1 chorrito de vinagre\n" +
                "Comino\n" +
                "Orégano","1.- Comenzamos cociendo la pasta en abundante agua con un poquito de sal como indique el fabricante. Yo he usado la típica pasta especial para ensaladas. 2.- Una vez tengamos la pasta cocida. La pasamos por agua fría y así cortamos la cocción para que tengamos la pasta fría y no templada..\n" +
                "\n" +
                "3.- Ahora por otra parte cocemos los huevos en agua durante 10 minutos.\n" +
                "\n" +
                "4.- Seguidamente picamos el resto de ingredientes que haya que picar y sacamos de la lata el maíz, el atún etc… 5.- Mezclamos muy bien todo y aliñamos con aceite de oliva y vinagre. Ponemos las especias que más nos gusten.","3",
                "Cal",

                FirebaseAuth.getInstance().getUid(),

                "nidea", ""));
        add(new Receptes("Arros al curry amb pollastre", "- 1 ceba gran\n" +
                "- 1 pebrot vermell\n" +
                "- 2 pastanagues\n" +
                "- 1 carabassó\n" +
                "- 2 cullerades de tomàquet fregit\n" +
                "- 1 tassa d'aigua\n" +
                "- 1 tassa llet de coco\n" +
                "- 2 pits de pollastre\n" +
                "- 2 culleradetes de curry en pols\n" +
                "- 1 cabeça d'all\n" +
                "- 4 tasses d'arròs","Per al sofregit\n" +
                "1. Tallem la ceba i el pebrot a tires.\n" +
                "2. Tallem el pollastre a daus.\n" +
                "3. Ratllem les pastanagues i el carabassó.\n" +
                "4. Posem en una cassola les verdures i les deixem coure amb una tassa d'aigua.\n" +
                "5. Hi afegim el tomàquet, la llet de coco i el curry. Ho deixem coure 10 minuts.\n" +
                "6. Hi afegim el pollastre i quan vegem que estigui fet, ho retirem del foc.\n" +
                "\n" +
                "Per l'arròs\n" +
                "1. Posem aigua a bullir en una olla amb oli, sal i una cabeça d'all.\n" +
                "2. Hi afegim l'arròs quan l'aigua faci bombolles grans i ho remenem durant 25 minuts fins que es faci.\n" +
                "3. Colem l'arròs i el remullem amb aigua freda.\n" +
                "4. Tornem a posar l'arròs a l'olla amb un raig d'oli i l'escalfem per poder-lo servir.\n" +
                "\n" +
                "Per servir-lo, ho farem en un mateix plat: el pollastre amb el sofregit en un costat del plat, i a l'altre costat l'arròs. Posteriorment cadascú s'ho podrà remenar per integrar-ho.","4",
                "Cal",

                FirebaseAuth.getInstance().getUid(),

                "nidea", ""));
        add(new Receptes("Canelons d'espinacs", "600 grams d’espinacs\n" +
                "1/2 ceba\n" +
                "150 grams de formatge fresc\n" +
                "25 grams aprox. de pinyons\n" +
                "25 grams aprox de panses\n" +
                "pasta per fer canelons\n" +
                "1/2 litre de llet sencera\n" +
                "40 grams de mantega\n" +
                "40 grams de farina de blat\n" +
                "formatge emmental ratllat\n" +
                "oli d’oliva\n" +
                "sal, pebre blanc i nou moscada","Primer saltegem els espinacs uns minuts en una paella gran amb una mica d’oli i després els posem en un colador perquè deixin anar l’aigua sobrant.\n" +
                "En la mateixa paella, cuinem la ceba tallada ben petita fins que transparenti i aleshores li afegim els pinyons, fins que s’enrosseixin una mica, i després les panses, que saltegem un minut.\n" +
                "Mentre es refreden els espinacs, preparem la salsa beixamel: en una cassola posem la mantega i quan s’hagi fos, afegim poc a poc la farina, remenant constantment fins que agafi una mica de color; aleshores comencem a afegir poc a poc la llet, que prèviament haurem escalfat, i continuarem remenant fins que quedin tot els ingredients ben lligats; afegim sal, un pèl de nou moscada i la mantenim a foc suau uns 15 minuts i ja estarà llesta per fer servir.\n" +
                "En un bol posem els espinacs després d’haver-los escorregut molt bé amb les mans -deixaran anar força aigua, i així evitem que facin malbé el plat-; després afegim la ceba amb les panses i els pinyons, i després el formatge fresc i mesclem tot els ingredients; aboquem a la mescla 3 cullerades de salsa beixamel i tastem el farcit per ajustar-lo de sal i pebre.\n" +
                "Encenem el forn a 200º i comencem a farcir la pasta de canelons. Posem els canelons farcits en una safata per anar al forn, els cobrim amb beixamel i per sobre repartim el formatge emmental ratllat i enfornem uns 15 minuts; passat aquest temps, posem el grill per gratinar el formatge, uns 5 minuts, però no us despisteu no sigui que es torrin massa. Servim calents.","5",
                "Cal",

                FirebaseAuth.getInstance().getUid(),

                "nidea", ""));
        add(new Receptes("Ensaladilla russa", "2-3 patatas (450 g)\n" +
                "4 zanahorias\n" +
                "2 huevos\n" +
                "20 aceitunas rellenas\n" +
                "3 cucharadas de guisantes en conserva\n" +
                "2 latas de atún en aceite (200 g)\n" +
                "2/4 de mayonesa casera\n" +
                "sal\n" +
                "perejil", "Pon las patatas (limpia y con piel) a cocer a fuego suave en una cazuela con agua fría. Pela las zanahorias y añádelas. Déjalas cocer (patatas y zanahorias) durante 25 minutos. A los 25 minutos de cocción,introduce los huevos, 1 cucharada de sal y deja cocer durante 10 minutos más.\n" +
                "\n" +
                "Escurre el agua, deja que se temple todo. Reserva las zanahorias en un plato y pela las patatas y el huevo.\n" +
                "\n" +
                "Pica la patata y el huevo en daditos. Corta las zanahorias en 4 cuartos a lo largo. Apila los trozos y córtalas perpendicularmente hasta conseguir trocitos pequeños.\n" +
                "\n" +
                "Corta las aceitunas por la mitad y después finamente.\n" +
                "\n" +
                "Pon la patata, el huevo, la zanahoria y las aceitunas en un cuenco grande, agrega los guisantes y el atún desmigado.Incorpora la mayonesa, mezcla suavemente. Prueba, pon a punto de sal y sirve. Adorna con una rama de perejil.", "6",
                "Cal",

                FirebaseAuth.getInstance().getUid(),

                "nidea",""));
        add(new Receptes("Pizza vegetariana", "1 cebolla mediana.\n" +
                "1 calabacín.\n" +
                "2 pimientos verdes.\n" +
                "1 berenjena.\n" +
                "Salsa de tomate para cubrir la masa.\n" +
                "1 masa de pizza. Sigue esta receta si prefieres masa de pizza fina, o esta si quieres una masa de pizza esponjosa. En este caso hemos hecho una fina, para que la protagonista sea la verdura.\n" +
                "1 sobre de mozzarella de búfala.\n" +
                "Tápenas (o alcaparras, es lo mismo) y olivas negras al gusto.\n" +
                "Aceite, sal y orégano.", "Prepara la masa si la prefieres casera. Puedes hacer una masa de pizza fina o esponjosa, elige tu receta. Si prefieres utilizar una masa precocida, ahorrarás algo de tiempo pero no sabrá igual.\n" +
                "Corta la berenjena en daditos y ponla en un recipiente apto para microondas. Cuece en el microondas, con el recipiente un poco tapado, durante 5 minutos. Añade a la sartén y dales vueltas durante 3-4 minutos, para que se doren un poquito, con una pizca de aceite y sal. Reserva.\n" +
                "\n" +
                "Corta el calabacín en rodajas lo más anchas posible, y ponlas en la sartén con un poquito de aceite y sal. Cuando se doren un poco por la parte de abajo, dales la vuelta, espera a que vuelvan a dorarse y reserva.\n" +
                "Pela la cebolla, córtala en tiritas y, en una sartén con muy poquito aceite y sal, hazla durante unos 5 minutos, hasta que empiece a transparentarse. Reserva.\n" +
                "Corta el pimiento verde en rodajas y sofrielo un poco en la sartén. Reserva.\n" +
                "\n" +
                "Mientras estiras la masa sobre papel de horno pon el horno a precalentar a 200ºC. Si no tienes que estirarla, procura precalentar el horno durante 10 minutos antes de meter la pizza.\n" +
                "Extiende el tomate sobre la base de pizza, e incorpora las rodajas de calabacín de forma que cubras casi toda la masa.\n" +
                "Mezcla el resto de verduras y añádelas.\n" +
                "Pon las tápenas y las olivas por encima, orégano al gusto, y el queso de mozzarella de búfala en tiritas.\n" +
                "\n" +
                "Mete la pizza con el papel en el horno a una altura media durante 10-12 minutos, hasta que el queso y la masa empiecen a dorarse.", "7",
                "Cal",

                FirebaseAuth.getInstance().getUid(),

                "nidea", ""));
        add(new Receptes("Lasanya", "Un paquet de pasta de lasanya fresca\n" +
                "1 porro\n" +
                "2 cebes tendres\n" +
                "3 grans d'all\n" +
                "1 kg de carn de vedella picada\n" +
                "julivert\n" +
                "150 g de parmesà\n" +
                "vi ranci\n" +
                "orenga fresca\n" +
                "alfàbrega fresca\n" +
                "150 g de mozzarella\n" +
                "6 tomàquets madurs\n" +
                "1 all\n" +
                "una cullerada de pebre vermell dolç\n" +
                "oli d'oliva verge extra\n" +
                "50 g de mantega\n" +
                "50 g de farina\n" +
                "350 ml de llet fresca\n" +
                "nou moscada", "Ratllem 6 tomàquets madurs i tallem un gra d'all. Fregim l'all i el tomàquet ratllat amb oli d'oliva verge extra i una cullerada de pebre vermell dolç.\n" +
                "Sofregim a foc lent la ceba i el porro. Un cop suat, hi afegim l'all picat (sense germinat). Al cap d'uns 5 minuts, hi posem la carn i apugem el foc. Un cop rostit, hi afegim el vi ranci, l'orenga fresca picada i el julivert picat. \n" +
                "Fem beixamel amb la barreja de mantega i farina. Quan estigui ben lligat, hi afegim la llet fresca i la nou moscada ratllada. Ho remenem.\n" +
                "A tota aquesta barreja, hi afegirem la meitat de la barreja de la salsa aurora (salsa de tomàquet i beixamel). \n" +
                "Fem capes posant una mica de salsa aurora i parmesà ratllat a la safata que anirà al forn, una làmina de pasta, una capa de carn, una capa de pasta, una altra de salsa aurora, més parmesà ratllat, una altra capa de pasta, una de carn, una de pasta, una de salsa aurora, formatge parmesà ratllat i mozzarella tallada a rodanxes. Al forn a 200º, 30 minuts. \n" +
                "Ho servim amb alfàbrega tallada per sobre.", "8",
                "Cal",

                FirebaseAuth.getInstance().getUid(),

                "nidea", ""));
        add(new Receptes("Sopa de peix", "Un quilo de peix de roca, uns crancs i un cap de rap.\n" +
                "Sis gambes i 12 musclos.\n" +
                "Unes avellanes i ametlles torrades.\n" +
                "Una ceba mitjaneta.\n" +
                "Una tomaca mitjaneta vermella i dolça.\n" +
                "Una fulla de llorer.\n" +
                "Aigua (1,5 litres), oli d'0liva verge, i sal.\n" +
                "Un gra d'all.\n" +
                "Unes gotetes d'anís.\n" +
                "Una rameta de safrà (poc).\n" +
                "Unes llesquetes de pa torrat. Pot ser pa del dia abans.", "En una cassola gran, posarem l'oli, i sofregirem una estona el peix (si és fresc, no cal rentar-lo massa),\n" +
                "Quan comenci a desfer-se, afegirem l'aigua i deixarem que bulli amb la fulleta de llorer i la rameta de safrà durant uns vint minuts.\n" +
                "Posarem els musclos al vapor, i els treurem de la closca. Els reservarem.\n" +
                "Sofregirem les gambes, les pelarem i els hi treurem els caps, que posarem també dins el brou.\n" +
                "Mirarem d'aprofitar alguna molla del peix bullit, (mirant que no hi quedi cap espina) i ho reservarem. Hi ha bastanta molla al cap del rap.\n" +
                "Passarem aquest brou per un colador xinès, desfent tot el peix, apretant-lo bé, per tal que deixi anar el màxim de gust possible, amb l'ajut de la mà de fusta (de punxa) del mateix colador.\n" +
                "En la mateixa cassola que hem sofregit les gambes, hi posarem la ceba ratllada, i la sofregirem. Quan comenci a dorar-se, hi afegirem també la tomaca ratllada i el granet d'all.\n" +
                "Quan aquest sofregit estigui al punt, hi tirarem el brou que ja haurem colat bé, i les llesquetes de pa torrades, juntament amb les ametlles i avellanes, prèviament picades a la picadora o morter.\n" +
                "Ho deixarem bullir una estona, mirant que no se'ns agafi a la cassola. Ho anirem remenant amb una batedora manual (no elèctrica), per desfer el pa.\n" +
                "Hi afegirem finalment els musclos, les molletes de peix, i les gambes, i ho deixarem bullir uns dos minuts més.\n" +
                "Per acabar, hi tirarem les gotetes d'anís, i ho taparem fins l'hora de servir-ho.","9",
                "Cal",

                FirebaseAuth.getInstance().getUid(),

                "nidea", ""));
        add(new Receptes("Macarrons boloñesa", "6 grapats de macarrons (200 g)\n" +
                "4 cullerades soperes de salsa de tomàquet fregit (80 g)\n" +
                "100 g de carn picada (mida aproximada a la d’una hamburguesa)\n" +
                "2 cullerades soperes de formatge ratllat (20 g)\n" +
                "2 cullerades soperes d’oli d’oliva (20 g)\n" +
                "Orenga\n" +
                "Sal", "Bulliu els macarrons en una olla amb aigua abundant uns 12 minuts. Afegiu una mica de sal i un rajolí d’oli. Quan estiguin cuits escorreu-los i poseu-los en un bol.\n" +
                "Mentre la pasta bull, ja podeu preparar la salsa.\n" +
                "Poseu una paella al foc amb un raig d’oli i afegiu-hi la carn picada.\n" +
                "Deixeu coure fins que estigui feta i remeneu de tant en tant perquè no es cremi.\n" +
                "Un cop feta afegiu-hi la salsa de tomàquet i barregeu-la bé.\n" +
                "Presenteu els macarrons al plat i pel damunt poseu-hi la salsa, un polsim d’orenga i el formatge ratllat.", "10",
                "Cal",

                FirebaseAuth.getInstance().getUid(),

                "nidea" ,""));
        add(new Receptes("Oliaigu amb figues", "2 cebes\n" +
                "1/2 pebre vermell\n" +
                "1 pebre verd\n" +
                "10 tomàtics\n" +
                "Oli d'oliva\n" +
                "Pa per sopes\n" +
                "Figues", "Posar dins un tià de terra o una cacerola un bon raig d'oli i sofregir i quan\n" +
                "estiguin un poc dauradets afegir la ceba. Quan estigui transparent, posar-hi els dos\n" +
                "pebres, i coure-ho durant una hora. Afegir lel tomàtic tallat a trossos sense pell i\n" +
                "coure-ho tot junt durant prop de dues hores més, a foc lent, per tal que quedi ben\n" +
                "confitat. Tapar-ho d'aigua, i escalfar, sense que bulli (açò és molt important).\n" +
                "Prèviament s'haurà anat posant sal al gust, preferiblement en les diferents parts de la\n" +
                "cocció.\n" +
                "Convé que reposi un dia, és a dir, fer-ho el dia abans de menjar-ho. Quan es serveixi,\n" +
                "posar les llesques de pa al fons del plat i posar-hi l'Oliaigua damunt. Es pot menjar\n" +
                "amb figues, amb altres fruites (per exemple, meló) o amb patates fregides.", "11",
                "Cal",

                FirebaseAuth.getInstance().getUid(),

                "nidea", ""));
    }
}
