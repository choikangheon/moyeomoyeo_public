import React, {useState} from 'react';
import AddressSearch from '../../../api/AddressSearch';

const TestCompo = () => {
  return(
      <div>
          {/*<AddressSearch address="부천시 소사로 56"/>*/}
          <AddressSearch address="인천시 부평구 평천로 305번길 15"/>
       </div>
  );
}


export default TestCompo;
