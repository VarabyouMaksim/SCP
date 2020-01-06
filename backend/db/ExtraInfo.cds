using Refrigerator from './Refrig';
using Rid from './Refrig';

		entity Comments {
		    key cmid : Rid;
		    usid : String(4);
		    text : String(100);

    		toRefrig : association to one Refrigerator on toRefrig.usid = usid;
		};

		entity Shelf {
		    key shid : Rid;
		    usid : String(4);
		    material : String(100);

    		toRefrig : association to one Refrigerator on toRefrig.usid = usid;
		};

		
