package com.aitshiroku.thai_alphabet_block;

import java.util.ArrayList;
import java.util.List;

public final class ThaiAlphabetDefinitions {

    public enum CharacterType {
        CONSONANT,
        VOWEL,
        TONE,
    }

    public record CharacterDef(
        String id,
        String thaiSymbol,
        String englishName,
        CharacterType type
    ) {}

    public static final List<CharacterDef> CONSONANTS = List.of(
        new CharacterDef(
            "empty_block",
            "",
            "Empty Block",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_ko_kai",
            "ก",
            "Ko Kai",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_kho_khai",
            "ข",
            "Kho Khai",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_kho_khuat",
            "ฃ",
            "Kho Khuat",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_kho_khwai",
            "ค",
            "Kho Khwai",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_kho_khon",
            "ฅ",
            "Kho Khon",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_kho_ra_khang",
            "ฆ",
            "Kho Ra Khang",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_ngo_ngu",
            "ง",
            "Ngo Ngu",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_cho_chan",
            "จ",
            "Cho Chan",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_cho_ching",
            "ฉ",
            "Cho Ching",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_cho_chang",
            "ช",
            "Cho Chang",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_so_so",
            "ซ",
            "So So",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_cho_choe",
            "ฌ",
            "Cho Choe",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_yo_ying",
            "ญ",
            "Yo Ying",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_do_chada",
            "ฎ",
            "Do Chada",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_to_patak",
            "ฏ",
            "To Patak",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_tho_than",
            "ฐ",
            "Tho Than",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_tho_nangmontho",
            "ฑ",
            "Tho Nangmontho",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_tho_phuthao",
            "ฒ",
            "Tho Phuthao",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_no_nen",
            "ณ",
            "No Nen",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_do_dek",
            "ด",
            "Do Dek",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_to_tao",
            "ต",
            "To Tao",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_tho_thung",
            "ถ",
            "Tho Thung",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_tho_thahan",
            "ท",
            "Tho Thahan",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_tho_thong",
            "ธ",
            "Tho Thong",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_no_nu",
            "น",
            "No Nu",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_bo_baimai",
            "บ",
            "Bo Baimai",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_po_pla",
            "ป",
            "Po Pla",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_pho_phueng",
            "ผ",
            "Pho Phueng",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_fo_fa",
            "ฝ",
            "Fo Fa",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_pho_phan",
            "พ",
            "Pho Phan",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_fo_fan",
            "ฟ",
            "Fo Fan",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_pho_samphao",
            "ภ",
            "Pho Samphao",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_mo_ma",
            "ม",
            "Mo Ma",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_yo_yak",
            "ย",
            "Yo Yak",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_ro_ruea",
            "ร",
            "Ro Ruea",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_lo_ling",
            "ล",
            "Lo Ling",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_wo_waen",
            "ว",
            "Wo Waen",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_so_sala",
            "ศ",
            "So Sala",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_so_rusi",
            "ษ",
            "So Rusi",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_so_suea",
            "ส",
            "So Suea",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_ho_hip",
            "ห",
            "Ho Hip",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_lo_chula",
            "ฬ",
            "Lo Chula",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_o_ang",
            "อ",
            "O Ang",
            CharacterType.CONSONANT
        ),
        new CharacterDef(
            "consonant_ho_nokhuk",
            "ฮ",
            "Ho Nokhuk",
            CharacterType.CONSONANT
        )
    );

    public static final List<CharacterDef> VOWELS = List.of(
        new CharacterDef("vowel_sara_a", "ะ", "Sara A", CharacterType.VOWEL),
        new CharacterDef("vowel_sara_aa", "า", "Sara Aa", CharacterType.VOWEL),
        new CharacterDef("vowel_sara_am", "ำ", "Sara Am", CharacterType.VOWEL),
        new CharacterDef("vowel_sara_i", "ิ", "Sara I", CharacterType.VOWEL),
        new CharacterDef("vowel_sara_ii", "ี", "Sara Ii", CharacterType.VOWEL),
        new CharacterDef("vowel_sara_ue", "ึ", "Sara Ue", CharacterType.VOWEL),
        new CharacterDef(
            "vowel_sara_uee",
            "ื",
            "Sara Uee",
            CharacterType.VOWEL
        ),
        new CharacterDef("vowel_sara_u", "ุ", "Sara U", CharacterType.VOWEL),
        new CharacterDef("vowel_sara_uu", "ู", "Sara Uu", CharacterType.VOWEL),
        new CharacterDef("vowel_sara_e", "เ", "Sara E", CharacterType.VOWEL),
        new CharacterDef("vowel_sara_ae", "แ", "Sara Ae", CharacterType.VOWEL),
        new CharacterDef("vowel_sara_o", "โ", "Sara O", CharacterType.VOWEL),
        new CharacterDef(
            "vowel_sara_ai_maimuan",
            "ใ",
            "Sara Ai Mai Muan",
            CharacterType.VOWEL
        ),
        new CharacterDef(
            "vowel_sara_ai_maimalai",
            "ไ",
            "Sara Ai Mai Malai",
            CharacterType.VOWEL
        ),
        new CharacterDef(
            "vowel_sara_ue_short",
            "ั",
            "Mai Hanakat",
            CharacterType.VOWEL
        ),
        new CharacterDef(
            "vowel_mai_taikhu",
            "็",
            "Mai Taikhu",
            CharacterType.VOWEL
        )
    );

    public static final List<CharacterDef> TONES = List.of(
        new CharacterDef("tone_mai_ek", "่", "Mai Ek", CharacterType.TONE),
        new CharacterDef("tone_mai_tho", "้", "Mai Tho", CharacterType.TONE),
        new CharacterDef("tone_mai_tri", "๊", "Mai Tri", CharacterType.TONE),
        new CharacterDef(
            "tone_mai_chattawa",
            "๋",
            "Mai Chattawa",
            CharacterType.TONE
        ),
        new CharacterDef(
            "tone_thanthakhat",
            "์",
            "Thanthakhat",
            CharacterType.TONE
        )
    );

    private ThaiAlphabetDefinitions() {}

    public static List<CharacterDef> all() {
        List<CharacterDef> allEntries = new ArrayList<>(
            CONSONANTS.size() + VOWELS.size() + TONES.size()
        );
        allEntries.addAll(CONSONANTS);
        allEntries.addAll(VOWELS);
        allEntries.addAll(TONES);
        return List.copyOf(allEntries);
    }
}
