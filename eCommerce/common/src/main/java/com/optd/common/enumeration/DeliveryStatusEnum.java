package com.optd.common.enumeration;

public enum DeliveryStatusEnum {
    SİPARİŞİNİZ_ALINDI((short) 1, "Siparişiniz Alındı"),
    HAZIRLANIYOR((short) 2, "Hazırlanıyor"),
    KARGOYA_TESLİM_EDİLDİ((short) 3, "Kargoya Teslim Edildi"),
    IPTAL((short) 5, "İPTAL"),
    TESLİM_EDİLDİ((short) 4, "Teslim Edildi");

    private Short id;
    private String value;

    DeliveryStatusEnum(Short id, String value) {
        this.id = id;
        this.value = value;
    }

    public Short getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
