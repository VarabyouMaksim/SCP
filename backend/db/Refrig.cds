type Rid : String(4);
using Comments from './ExtraInfo';
using Shelf from './ExtraInfo';

entity Refrigerator {
    key usid : Rid;
    name : String(100);
    brand : String(100);

    toComment : association to many Comments on toComment.usid = usid;
    toShelf : association to one Shelf on toShelf.usid = usid;
};
