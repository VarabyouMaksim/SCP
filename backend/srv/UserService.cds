using Refrigerator as _Refrigerator from '../db/Refrig';
using Comments as _Comment from '../db/ExtraInfo';
using Shelf as _Shelf from '../db/ExtraInfo';

service odata {

  entity Refrigerators as projection on _Refrigerator;	

  entity Comment @(
		title: 'Comment',
		Capabilities: {
			InsertRestrictions: {Insertable: false},
			UpdateRestrictions: {Updatable: false},
			DeleteRestrictions: {Deletable: false}
		}
	) as projection on _Comment;

    entity Shelf @(
		title: 'Shelf',
		Capabilities: {
			InsertRestrictions: {Insertable: false},
			UpdateRestrictions: {Updatable: false},
			DeleteRestrictions: {Deletable: false}
		}
	) as projection on _Shelf;

}
