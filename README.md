# 🏰 Únik z Prokletého zámku

**Autor:** Slávek Slíva (třída C2b)  
**Žánr:** Textová příběhová hra

---

## 📖 Příběh
Hlavní postava, obyčejný mladík **Pepa Bojka**, se jednoho dne vydal do temného lesa, kde narazil na velký tmavý zámek. Ze zvídavosti vstoupil dovnitř, ale v tu ránu se za ním dveře zabouchly a zamkly. 

V útrobách zámku potkává mluvící ropuchu a mocného čaroděje. Aby získal svobodu, musí splnit dvě podmínky:
1. Najít **Starý čarovný lexikon** a donést ho čaroději, aby zrušil kletbu.
2. Najít **Velký starý klíč**, kterým odemkne hlavní bránu.

## 🎮 Ovládání a příkazy
Všechny příkazy musí začínat znakem `-`. Hra je tolerantní k velikosti písmen.

### Seznam příkazů a jejich formát:

| Příkaz | Formát | Popis |
| :--- | :--- | :--- |
| **Pohyb** | `-jdi [místnost]` | Přesun mezi sousedními místnostmi. |
| **Průzkum** | `-prohledej` | Vypíše popis místnosti a seznam věcí v ní. |
| **Dialog** | `-mluv` | Zahájí rozhovor s postavou (NPC) v místnosti. |
| **Sebrání** | `-vezmi [předmět]` | Přidá konkrétní předmět do inventáře (pokud je místo). |
| **Zahození** | `-zahod [předmět]` | Navždy odstraní vybraný předmět z inventáře. |
| **Akce** | `-akce [předmět]` | Použití předmětu. Pokud otevíráte **truhlu**, pište pouze `-akce`. Pokud chcete akci s nějakým předmětem, tak napiště `-akce [předmět]` |
| **Nápověda** | `-napoveda` | Připomene aktuální cíl hry a aktuální situaci hráče, jako polohu a inventář. |
| **Pomoc** | `-pomoc` | Zobrazí seznam všech příkazů. |
| **Ukončení** | `-konec [parametr]` | Pro ukončení s uložením napište: `true`, `ano`, `ulozit` nebo `pravda`, jinak se hra neuloží. |

---

## 📁 Manipulace se soubory
Pro správné fungování hry je nezbytné **dodržet přesnou strukturu a formát** všech doprovodných textových či konfiguračních souborů. Jakýkoliv neodborný zásah do obsahu souborů (např. napsání špatného formátu nebo porušení struktury řádků) může vést k pádům aplikace nebo chybám v generování předmětů.

* **Originální soubory** a aktuální správné soubory naleznete v   🔗  [originálním repozitáři na GitHubu.](https://github.com/SlivaSlavek/ProjektSlivaUnikZamek)

---

## 🛠️ Instalace a spuštění
1. Stáhněte si obsah repozitáře z výše uvedeného odkazu na GitHubu.
2. Ujistěte se, že máte nainstalované potřebné prostředí (např. IntelliJ IDEA).
3. Spusťte hlavní spustitelný soubor aplikace.
4. Hra vás přivítá úvodním textem v místnosti **Vstup** a řekne Vám, co dál.

---

## 💡 Technické poznámky
* **Dynamické NPC:** Duchové a jiná NPC se pohybují náhodně. Pokud je v místnosti nevidíte, zkuste se vrátit později nebo projít jinou částí zámku.
* **Inventář:** Kapacita je omezena na 2 sloty. Pokud najdete důležitý předmět (Klíč/Lexikon) a máte plno, musíte něco obětovat příkazem `-zahod`.
* **Ukládání:** Hra ukládá stav světa a inventáře pouze tehdy, pokud je ukončena správným parametrem u příkazu `-konec`.
* **Chyby:** Pokud něco nebude fungovat, je to pravděpodobně chybnou manipulací se soubory. Opravíte přestažením hry.
* **Spustitelnost:** Ač hra má .jar soubor, není kvůli funkčnostem hry se soubroy funkční, tudíž je potřeba používat kód ve vývojovém prostředí.

---

Toto je konec README, užijte si nyní hru :)
