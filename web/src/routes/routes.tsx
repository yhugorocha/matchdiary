import { BrowserRouter, Route, Routes } from "react-router-dom";
import ReportsPage from "../pages/report/ReportsPage";
import MatchListPage from "../pages/match/MatchListPage";
import TeamListPage from "../pages/team/TeamListPage";
import AddTeamPage from "../pages/team/AddTeamPage";
import EditTeamPage from "../pages/team/EditTeamPage";
import AddMatchPage from "../pages/match/AddMatchPage";
import EditMatchPage from "../pages/match/EditMatchPage";

function AllRoutes() {
    return (
      <>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<ReportsPage/>}/>

                <Route path="/team/list" element={<TeamListPage/>}/>
                <Route path="/team/add" element={<AddTeamPage/>}/>
                <Route path="/team/edit/:id" element={<EditTeamPage/>}/>

                <Route path="/reports/:id" element={<ReportsPage/>}/>

                <Route path="/match/list" element={<MatchListPage/>}/>
                <Route path="/match/add" element={<AddMatchPage/>}/>
                <Route path="/match/edit" element={<EditMatchPage/>}/>
            </Routes>
        </BrowserRouter>
      </>
    );
  }
  
  export default AllRoutes;