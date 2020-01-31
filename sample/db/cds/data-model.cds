namespace com.leverx.sample;

using cuid from '@sap/cds/common';

entity Shelf : cuid {
	material : String not null;
	type : String not null;
    refrigerator : Association to Refrigerator;
    inspected : Boolean default true;
}

entity Refrigerator : cuid {
    brand : String not null;
    Volume : DecimalFloat not null;
    UoM : String not null;
    big: Boolean default false;
    shelfs: Association to many Shelf on shelfs.refrigerator = $self;
}