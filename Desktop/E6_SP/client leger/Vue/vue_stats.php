<link rel="stylesheet" href="Style/style_mes_apparts.css">

<div class="container mes-apparts-container">

    <div class="page-header">
        <div>
            <h1><i class="fas fa-chart-bar"></i> Statistiques</h1>
            <p class="page-sub">Prix min, max et moyen par type de logement</p>
        </div>
    </div>

    <?php if(empty($stats)): ?>
        <div class="empty-state">
            <i class="fas fa-chart-bar"></i>
            <p>Aucune statistique disponible pour le moment.</p>
        </div>
    <?php else: ?>
        <table style="width:100%; border-collapse:collapse; margin-top:20px;">
            <thead>
                <tr style="background:#1A3A5C; color:white;">
                    <th style="padding:12px 16px; text-align:left;">Type de logement</th>
                    <th style="padding:12px 16px; text-align:center;">Nb logements</th>
                    <th style="padding:12px 16px; text-align:center;">Prix min / semaine</th>
                    <th style="padding:12px 16px; text-align:center;">Prix max / semaine</th>
                    <th style="padding:12px 16px; text-align:center;">Prix moyen / semaine</th>
                </tr>
            </thead>
            <tbody>
                <?php foreach($stats as $i => $ligne): ?>
                    <tr style="background:<?= $i % 2 === 0 ? '#F5F7FA' : '#FFFFFF' ?>;">
                        <td style="padding:12px 16px; font-weight:bold; color:#1A3A5C;">
                            <i class="fas fa-home"></i> <?= htmlspecialchars($ligne['type_appart']) ?>
                        </td>
                        <td style="padding:12px 16px; text-align:center;">
                            <?= $ligne['nb_apparts'] ?>
                        </td>
                        <td style="padding:12px 16px; text-align:center; color:#27ae60; font-weight:bold;">
                            <?= number_format($ligne['prix_min'], 2, ',', ' ') ?> €
                        </td>
                        <td style="padding:12px 16px; text-align:center; color:#e74c3c; font-weight:bold;">
                            <?= number_format($ligne['prix_max'], 2, ',', ' ') ?> €
                        </td>
                        <td style="padding:12px 16px; text-align:center; color:#2E6DA4; font-weight:bold;">
                            <?= number_format($ligne['prix_moyen'], 2, ',', ' ') ?> €
                        </td>
                    </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    <?php endif; ?>
</div>