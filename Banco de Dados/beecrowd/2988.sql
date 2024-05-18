SELECT 
   team_name AS name,	
   COUNT(team) AS matches,
   SUM(CASE WHEN (team_gols > adv_gols) THEN 1 ELSE 0 END) AS victories,
   SUM(CASE WHEN (team_gols < adv_gols) THEN 1 ELSE 0 END) AS defeats,
   SUM(CASE WHEN (team_gols = adv_gols) THEN 1 ELSE 0 END) AS draws,
   SUM(CASE
       WHEN (team_gols > adv_gols) THEN 3
       WHEN (team_gols = adv_gols) THEN 1 
       ELSE 0 
   END) AS score
FROM (
    SELECT 
        t1.name AS team_name, 
        m.id, 
        m.team_2 AS team, 
        m.team_1 AS adv, 
        m.team_2_goals AS team_gols, 
        m.team_1_goals AS adv_gols 
    FROM matches m 
    INNER JOIN teams t1 ON m.team_2 = t1.id
    UNION ALL
    SELECT 
        t2.name AS team_name, 
        m.id, 
        m.team_1 AS team, 
        m.team_2 AS adv, 
        m.team_1_goals AS team_gols, 
        m.team_2_goals AS adv_gols 
    FROM matches m 
    INNER JOIN teams t2 ON m.team_1 = t2.id
) AS ut
GROUP BY team, team_name
ORDER BY score DESC, team_name;