<link rel="stylesheet" href="Style/style_mes_apparts.css">

<div class="container form-appart-container">

    <a href="index.php?page=mes_contrats" class="btn-back">
        <i class="fas fa-arrow-left"></i> Retour à mes contrats
    </a>

    <div class="form-card">
        <h1><i class="fas fa-plus-circle"></i> Ajouter un contrat</h1>

        <form method="POST" action="index.php?page=ajout_contrat">
            <div class="form-grid">

                <div class="form-group">
                    <label>Appartement concerné</label>
                    <select name="id_appart" required>
                        <option value="">-- Choisir un appartement --</option>
                        <?php foreach($mesApparts as $appart): ?>
                            <option value="<?= $appart['id_appart'] ?>">
                                <?= htmlspecialchars($appart['type_appart']) ?> — <?= htmlspecialchars($appart['num_appart']) ?>
                            </option>
                        <?php endforeach; ?>
                    </select>
                </div>

                <div class="form-group">
                    <label>Tarif saison (€)</label>
                    <input type="number" name="tarif_saison" step="0.01" required placeholder="Ex: 3500.00">
                </div>

                <div class="form-group">
                    <label>Date de début <small style="color:#999">(doit être le 1er octobre)</small></label>
                    <input type="date" name="date_debut" required value="<?= date('Y') . '-10-01' ?>">
                </div>

                <div class="form-group">
                    <label>Date de fin <small style="color:#999">(doit être le 30 septembre)</small></label>
                    <input type="date" name="date_fin" required value="<?= (date('Y')+1) . '-09-30' ?>">
                </div>

            </div>

            <button type="submit" name="btnAjoutContrat" class="btn-submit">
                <i class="fas fa-save"></i> Enregistrer le contrat
            </button>
        </form>
    </div>
</div>