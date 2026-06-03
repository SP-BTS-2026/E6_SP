<link rel="stylesheet" href="Style/style_mes_apparts.css">

<div class="container mes-apparts-container">

    <div class="page-header">
        <div>
            <h1><i class="fas fa-file-contract"></i> Mes Contrats</h1>
            <p class="page-sub">Gérez vos contrats de mandat locatif</p>
        </div>
        <a href="index.php?page=ajout_contrat" class="btn-add">
            <i class="fas fa-plus"></i> Ajouter un contrat
        </a>
    </div>

    <?php if(isset($_GET['success'])): ?>
        <div class="alert-success">
            <i class="fas fa-check-circle"></i>
            <?php if($_GET['success'] == 'ajout') echo "Contrat ajouté avec succès !"; ?>
        </div>
    <?php endif; ?>

    <?php if(empty($mesContrats)): ?>
        <div class="empty-state">
            <i class="fas fa-file-contract"></i>
            <p>Vous n'avez pas encore de contrat.</p>
            <a href="index.php?page=ajout_contrat" class="btn-add">
                <i class="fas fa-plus"></i> Ajouter mon premier contrat
            </a>
        </div>
    <?php else: ?>
        <div class="apparts-grid">
            <?php foreach($mesContrats as $contrat): ?>
                <article class="appart-card-admin">
                    <div class="card-body">
                        <h3>
                            <i class="fas fa-home"></i>
                            <?= htmlspecialchars($contrat['type_appart']) ?> — <?= htmlspecialchars($contrat['num_appart']) ?>
                        </h3>
                        <div class="card-specs">
                            <span><i class="fas fa-calendar-alt"></i> Du <?= date('d/m/Y', strtotime($contrat['date_debut'])) ?></span>
                            <span><i class="fas fa-calendar-alt"></i> Au <?= date('d/m/Y', strtotime($contrat['date_fin'])) ?></span>
                        </div>
                        <div class="card-price-hebdo">
                            Tarif saison : <?= number_format($contrat['tarif_saison'], 2, ',', ' ') ?> €
                        </div>
                        <div style="margin-top:10px;">
                            <?php if($contrat['statut_archive']): ?>
                                <span style="background:#e74c3c;color:white;padding:4px 10px;border-radius:20px;font-size:0.8rem;">
                                    <i class="fas fa-archive"></i> Archivé
                                </span>
                            <?php else: ?>
                                <span style="background:#27ae60;color:white;padding:4px 10px;border-radius:20px;font-size:0.8rem;">
                                    <i class="fas fa-check"></i> Actif
                                </span>
                            <?php endif; ?>
                        </div>
                    </div>
                </article>
            <?php endforeach; ?>
        </div>
    <?php endif; ?>
</div>