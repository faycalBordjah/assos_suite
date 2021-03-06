import React from "react";
import { Admin, Resource} from 'react-admin';
import polyglotI18nProvider from 'ra-i18n-polyglot';
import frenchMessages from 'ra-language-french';
import dataProvider from './dataProvider'
import authProvider from './authProvider'
import {AdherentList} from './AdherentList'
import {GroupList} from './GroupList'
import "./App.css";
import {BACK_URL}  from "./constants";
import { httpClient } from "./authorization";
import { AdherentCreate } from "./AdherentCreate";
import {AdherentEdit} from './AdherentEdit'
import { AdherentShow } from "./AdhrentShow";
import customRoutes from './routes';
import Layout from "./layout/Layout";
import Welcome from "./dashboard/Welcome";
import { GroupCreate } from "./GroupCreate";
import { GroupEdit } from "./GroupEdit";
import {GroupShow} from "./GroupShow";
import SupervisorAccountIcon from '@material-ui/icons/SupervisorAccount';
import GroupWorkIcon from '@material-ui/icons/GroupWork';


const i18nProvider = polyglotI18nProvider(() => frenchMessages, 'fr');
const App = () => (
  <Admin title = "AssoSuite"
        dataProvider={dataProvider(BACK_URL, httpClient)}
        authProvider={authProvider}
        customRoutes={customRoutes}
        dashboard={Welcome}
        layout={Layout}
        i18nProvider={i18nProvider} >
    <Resource 
        name="adherents"
        title="ADHERENTS"
        list={AdherentList}
        create={AdherentCreate} 
        edit={AdherentEdit}
        show={AdherentShow}
        icon = {SupervisorAccountIcon}
        options={{ label: 'Mes Adhérents' }}
        >
    </Resource>
    <Resource 
        name="groups"
        list={GroupList}
        create={GroupCreate}
        edit={GroupEdit}
        show={GroupShow}
        icon = {GroupWorkIcon}
        options={{ label: 'Mes Groupes' }}
    >
    </Resource>
  </Admin>

)

export default App;
