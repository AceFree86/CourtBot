package com.acefreesoft.CourtBot;

public class MsgContainer {


    final String photoLogo = "photo/PRC.jpg";
    final String photoGoogleMaps = "photo/perechin1.jpg";
    final String photoAppleMaps = "photo/perechin.jpg";
    final String photoElectronicCourt = "photo/electroniccourt.jpg";

    final String nameBtn3 = "🔙Назат в меню";

    final String nameBtn1 = "🎫Пошук за П.І.П. або номером справи";
    final String nameBtn2 = "🗓️Пошук за датою";
    final String url = "https://starlit-marzipan-56ef4f.netlify.app";

    final String courtAppealBtn1 = "🎫Пошук за П.І.П. або номером справи.";
    final String courtAppealBtn2 = "🗓️Пошук за датою.";
    final String courtAppealUrl = "https://starlit-marzipan-56ef4f.netlify.app/appellate";

    final String uzhhorodCourtBtn1 = "🎫Пошук за П.І.П. або номером справи..";
    final String uzhhorodCourtBtn2 = "🗓️Пошук за датою..";
    final String uzhhorodCourtUrl = "https://starlit-marzipan-56ef4f.netlify.app/uzhhorod";

    final String velikobereznyanskyCourtBtn1 = "🎫Пошук за П.І.П. або номером справи...";
    final String velikobereznyanskyCourtBtn2 = "🗓️Пошук за датою...";
    final String velikobereznyanskyCourtUrl = "https://starlit-marzipan-56ef4f.netlify.app/greatberezny";

    final String nameBTN = "Розклад засідань";
    final String urlPr = "https://pr.zk.court.gov.ua/sud0708/gromadyanam/csz/";
    final String urlAppeal = "https://zka.court.gov.ua/sud4806/gromadyanam/csz/";
    final String urlUz = "https://ug.zk.court.gov.ua/sud0712/gromadyanam/csz/";
    final String urlVb = "https://vb.zk.court.gov.ua/sud0702/gromadyanam/csz/";
    final String callbackPr = "callbackPr";
    final String callbackAppeal = "callbackAppeal";
    final String callbackUz = "callbackUz";
    final String callbackVb = "callbackVb";

    final String msgDescription = """
            Я Віртуальний асистент <b>Перечинського районного суду</b>.
            Я вмію находити дату судового засідання за прізвищем, ім'ям і за номером справи, а також сповіщаю про дату і час засідання.""";
    final String msgInstruction = """
            Наше спілкування буде проходити так:
            - Вибираєте🕹 розділ в меню
            - Натискаєте📲 на кнопки в меню
            - А я Вам відпишу📨.""";
    final String msgContacts = """
             <u><b>контактні дані:</b></u>
                                     
            📍 E-mail📧: inbox@pr.zk.court.gov.ua;
                                     
            📍 Телефон📞: (03145)2-11-96;

            📍 Адреса📮: пл. Народна, 15, м. Перечин,
            \t\t\t\t\t\t89200.""";
    final String msgTravelCards = """
             карти проїзду 🚗. Натисніть на кнопку, щоб переміститися на бажану карту.
            """;
    final String msgGoingWeb = """
             перейшовши на вебпортал, можна передивитися всі оголошення про виклик до суду.
            """;
    final String msgElectronicCourt = """
             електронний суд дозволяє\s
            подавати учасникам судового процесу до суду документи в електронному вигляді, а також надсилати таким учасникам процесуальних документів в електронному вигляді,паралельно з документами у паперовому вигляді відповідно до процесуального законодавства.
             
            <a href="https://id.court.gov.ua/">Перейти в Електронний Суд🔗.</a>
             _______
            """;
    final String msgMobileApplication = """
             також Ви можете скористатися Нашим офіційним мобільним додатком <b>Електронного суду в Україні єСуд</b> призначеним для доступу до Електронного суду з мобільних пристроїв. <b>Для використання додатку Вам необхідно бути зареєстрованим в електронному кабінеті</b>.
             
            <a href="https://cabinet.court.gov.ua">Перейти в Електронний кабінет🔗.</a>
             _______
            """;
    final String msgMeetingDate = """
             в даному розділі можна подивитися дату засідання нажавши🕹 на ниже вказані кнопки👇:

            📍 Натискаємо📲 на 🎫Пошук за прізвищем та ім’ям (П.І.П.) - набираємо П.І. і відправляємо📥

            📍 Натискаємо📲 на 🔖Пошук за номером справи - набираємо 304/555/20 і відправляємо📥

            📍 Натискаємо📲 на 🗓️Пошук за датою - вибираємо дату і прізвище судді""";
    final String msgListCourt = """
             в даному розділі можна вибрати🕹 суд зі списка👇:

            📍 ⚖️Закарпатський апеляційний суд;

            📍 ⚖️Ужгородський міськрайонний суд;

            📍 ⚖️Великоберезнянський районний суд.
                                        
             Та подивитися дату засідання.""";
    final String msgInputInstruction = """
             набирати можна з малої букви, а апостроф в тексті заміняємо на пробіл.

            <b>Попередження</b>❗, якщо буде введено тільки прізвище або ім’я, то Вам видасть всі засідання призначені на дане прізвище або ім’я.""";

    final String msgInputPush = """
             в цьому розділі можна скористатися сервісом *<b>Сповіщення</b>*.
             
            Суть сервісу проста, Ви отримаєте📩 сповіщення про дату, час судового засідання.

            Щоб підписатися✍🏻 на сервіс Вам потрібно набрати⌨ прізвище та ім'я або номер справи і відправити📥.
            
            <b>Попередження</b>❗
            Сервіс <b>Сповіщення</b> поширюється на всі чотири судові установи.
            
            Прізвище та ім'я набирати можна з малої букви, а апостроф в тексті можна заміняти на пробіл.
            
            Номера справи набираємо⌨ відповідно до зразків 304/555/20 або 555/20.
            
            <b>Інформацію вказуйте правильно</b>💯.

            Щоб вийти натисніть на👉 🔙_Назат_""";
}
