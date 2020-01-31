namespace leverx.com.sample;

using com.leverx.sample as data from '../../db/index';

service ShelfService {

    @Capabilities : {
        Insertable : true,
        Updatable  : true,
        Deletable  : true
    }
    entity Shelf as projection on data.Shelf 
    actions {
        action inspect(inspected: Boolean);
    }
    
    @Capabilities : {
        Insertable : true,
        Updatable  : true,
        Deletable  : true
    }
    entity Refrigerator as projection on data.Refrigerator
    actions {
    	function shelfOwned() returns Integer;
    };

    function hasUninspectedShelf(refrigeratorId: String) returns Boolean;
    
}